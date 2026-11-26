package com.yo.elderlycarebooking.service.Impl;

import com.yo.elderlycarebooking.common.exception.BadRequestException;
import com.yo.elderlycarebooking.common.exception.ConflictException;
import com.yo.elderlycarebooking.common.exception.NotFoundException;
import com.yo.elderlycarebooking.domain.entity.Booking;
import com.yo.elderlycarebooking.domain.entity.CareRecipient;
import com.yo.elderlycarebooking.domain.entity.Caregiver;
import com.yo.elderlycarebooking.domain.entity.User;
import com.yo.elderlycarebooking.domain.enumm.PaymentStatusEnum;
import com.yo.elderlycarebooking.domain.enumm.ServiceTypeEnum;
import com.yo.elderlycarebooking.domain.enumm.StatusBookingEnum;
import com.yo.elderlycarebooking.domain.enumm.UserEnum;
import com.yo.elderlycarebooking.dto.booking.BookingRequest;
import com.yo.elderlycarebooking.dto.booking.BookingResponse;
import com.yo.elderlycarebooking.mapper.BookingMapper;
import com.yo.elderlycarebooking.repository.BookingRepository;
import com.yo.elderlycarebooking.repository.CareRecipientRepository;
import com.yo.elderlycarebooking.repository.CaregiverRepository;
import com.yo.elderlycarebooking.repository.auth.AuthUserRepository;
import com.yo.elderlycarebooking.service.BookingService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Set;
import java.util.concurrent.ThreadLocalRandom;

@Service
@RequiredArgsConstructor
public class BookingServiceImpl implements BookingService {

    private static final ZoneId VN_ZONE = ZoneId.of("Asia/Ho_Chi_Minh");
    private static final DateTimeFormatter CODE_DATE_FMT = DateTimeFormatter.ofPattern("yyMMdd");
    private static final Set<Integer> ALLOWED_DURATIONS = Set.of(2, 4, 6, 8, 12, 24);


    private final BookingRepository bookingRepository;
    private final AuthUserRepository userRepository;
    private final CareRecipientRepository careRecipientRepository;
    private final CaregiverRepository caregiverRepository;
    private final BookingMapper bookingMapper;


    @Override
    @Transactional
    public BookingResponse createBooking(String username, BookingRequest request) {
        User customer = findUserOrThrow(username);

        CareRecipient recipient = careRecipientRepository.findById(request.recipientId())
                .orElseThrow(() -> new NotFoundException("Không tìm thấy hồ sơ người cao tuổi với ID: " + request.recipientId()));

        if (!recipient.getCustomer().getId().equals(customer.getId())) {
            throw new AccessDeniedException("Bạn chỉ được đặt lịch cho hồ sơ người thân thuộc tài khoản của mình");
        }

        validateScheduleAndDuration(request);

        BigDecimal servicePrice = calculateListedPrice(request.serviceType(), request.durationHours());
        BigDecimal discount = request.discountAmount() != null ? request.discountAmount() : BigDecimal.ZERO;
        if (discount.compareTo(servicePrice) > 0) {
            throw new BadRequestException("Số tiền giảm giá không được vượt quá giá niêm yết của gói dịch vụ");
        }
        BigDecimal totalPrice = servicePrice.subtract(discount);
        LocalTime endTime = request.startTime().plusHours(request.durationHours());

        Booking booking = bookingMapper.toEntity(request);
        booking.setBookingCode(generateUniqueBookingCode());
        booking.setCustomer(customer);
        booking.setRecipient(recipient);
        booking.setEndTime(endTime);
        booking.setServicePrice(servicePrice);
        booking.setDiscountAmount(discount);
        booking.setTotalPrice(totalPrice);
        booking.setPaymentStatus(PaymentStatusEnum.UNPAID);

        return bookingMapper.toResponse(bookingRepository.save(booking));

    }
    // Khách hàng xem lịch sử đơn của mình
    @Override
    @Transactional
    public Page<BookingResponse> getMyBookings(String username, StatusBookingEnum status, Pageable pageable) {
        return bookingRepository.findMyBookings(username, status, pageable)
                //.map(booking -> bookingMapper.toResponse(booking));
                .map(bookingMapper::toResponse);
    }

    // Chăm sóc viên xem danh sách đơn đang chờ nhận việc (PENDING)
    @Override
    @Transactional
    public Page<BookingResponse> getAvailableBookings(ServiceTypeEnum serviceType, Pageable pageable) {
        return bookingRepository.findAvailableBookings(serviceType, pageable)
                .map(booking -> bookingMapper.toResponse(booking));
    }

    // Chăm sóc viên xem danh sách ca làm của mình
    @Override
    @Transactional
    public Page<BookingResponse> getCaregiverBookings(String username, StatusBookingEnum status, Pageable pageable) {
        return bookingRepository.findCaregiverBookings(username, status, pageable)
                .map(bookingMapper::toResponse);
    }

    @Override
    @Transactional
    public BookingResponse getBookingById(Long id, String username) {
        Booking booking = findBookingOrThrow(id);
        User currentUser = findUserOrThrow(username);
        validateViewPermission(booking, currentUser);
        return bookingMapper.toResponse(booking);

    }

    @Override
    @Transactional
    public BookingResponse acceptBooking(Long id, String username) {
        Booking booking = findBookingOrThrow(id);

        Caregiver caregiver = caregiverRepository.findByUserUsername(username)
                .orElseThrow(() -> new BadRequestException("Bạn cần tạo hồ sơ chuyên môn Caregiver trước khi nhận việc"));

        if (!Boolean.TRUE.equals(caregiver.getIsAvailable())) {
            throw new BadRequestException("Trạng thái nhận việc của bạn đang tắt. Vui lòng bật sẵn sàng nhận việc trước.");
        }
        if (booking.getStatus() != StatusBookingEnum.PENDING || booking.getCaregiver() != null) {
            throw new ConflictException("Đơn việc này đã có chăm sóc viên nhận hoặc không còn ở trạng thái chờ");
        }

        boolean hasOverlap = bookingRepository.existsOverlappingSchedule(
                caregiver.getId(),
                booking.getWorkDate(),
                booking.getStartTime(),
                booking.getEndTime(),
                booking.getDurationHours()
        );
        if (hasOverlap) {
            throw new ConflictException("Bạn đã có ca làm việc khác trùng khung giờ trong ngày " + booking.getWorkDate());
        }

        int updatedRows = bookingRepository.acceptBookingAtomically(id, caregiver);

        if (updatedRows == 0) {
            throw new ConflictException("Đơn việc vừa được chăm sóc viên khác nhận mất rồi, vui lòng chọn đơn khác!");
        }

        return bookingMapper.toResponse(findBookingOrThrow(id));

    }

    @Override
    @Transactional
    public BookingResponse checkIn(Long id, String username) {
        Booking booking = findBookingOrThrow(id);

        validateAssignedCaregiver(booking, username);

        if (booking.getStatus() != StatusBookingEnum.ASSIGNED) {
            throw new BadRequestException("Chỉ có thể chấm công bắt đầu ca khi đơn đang ở trạng thái ASSIGNED");
        }
        if (booking.getCheckInTime() != null) {
            throw new ConflictException("Đơn việc này đã được chấm công bắt đầu ca trước đó");
        }

        LocalDateTime scheduledStart = LocalDateTime.of(booking.getWorkDate(), booking.getStartTime());
        LocalDateTime earliestCheckIn = scheduledStart.minusMinutes(15);
        LocalDateTime latestCheckIn = scheduledStart.plusMinutes(30);
        LocalDateTime now = LocalDateTime.now(VN_ZONE);

        if (now.isBefore(earliestCheckIn)) {
            throw new BadRequestException(
                    "Chưa đến giờ chấm công. Bạn chỉ được phép bắt đầu ca từ 15 phút trước giờ hẹn (" + earliestCheckIn + ")"
            );
        }
        if (now.isAfter(latestCheckIn)) {
            throw new BadRequestException(
                    "Đã quá 30 phút sau thời gian bắt đầu ca (" + latestCheckIn + "), không thể chấm công"
            );
        }

        booking.setCheckInTime(Timestamp.valueOf(now));
        booking.setStatus(StatusBookingEnum.IN_PROGRESS);

        return bookingMapper.toResponse(bookingRepository.save(booking));

    }

    @Override
    @Transactional
    public BookingResponse checkOut(Long id, String username) {
        Booking booking = findBookingOrThrow(id);
        validateAssignedCaregiver(booking, username);

        if (booking.getStatus() != StatusBookingEnum.IN_PROGRESS || booking.getCheckInTime() == null) {
            throw new BadRequestException("Chỉ có thể kết thúc ca khi đơn đang ở trạng thái IN_PROGRESS và đã check-in");
        }

        booking.setCheckOutTime(Timestamp.valueOf(LocalDateTime.now(VN_ZONE)));
        booking.setStatus(StatusBookingEnum.COMPLETED);

        Caregiver caregiver = booking.getCaregiver();
        caregiver.setTotalJobsDone(caregiver.getTotalJobsDone() + 1);
        caregiverRepository.save(caregiver);

        // Uỷ quyền sang PaymentService xử lý hoàn tất thanh toán nếu đơn trả tiền mặt (UNPAID)
        // paymentService.completePendingPaymentsOnCheckout(booking);

        return bookingMapper.toResponse(bookingRepository.save(booking));

    }

    @Override
    @Transactional
    public BookingResponse cancelBooking(Long id, String username) {
        Booking booking = findBookingOrThrow(id);
        User currentUser = findUserOrThrow(username);

        if (currentUser.getRole() != UserEnum.ADMIN
                && !booking.getCustomer().getId().equals(currentUser.getId())) {
            throw new AccessDeniedException("Bạn không có quyền huỷ đơn đặt lịch của người khác");
        }

        if (booking.getStatus() != StatusBookingEnum.PENDING
                && booking.getStatus() != StatusBookingEnum.ASSIGNED) {
            throw new BadRequestException("Chỉ có thể huỷ đơn khi đang ở trạng thái PENDING hoặc ASSIGNED");
        }

        booking.setStatus(StatusBookingEnum.CANCELLED);

        // Uỷ quyền sang PaymentService xử lý hoàn tiền / huỷ giao dịch chờ
        // paymentService.handleBookingCancellation(booking);

        return bookingMapper.toResponse(bookingRepository.save(booking));

    }

    @Override
    @Transactional
    public Page<BookingResponse> getAllBookingsForAdmin(StatusBookingEnum status, PaymentStatusEnum paymentStatus, ServiceTypeEnum serviceType, String keyword, Pageable pageable) {
        String cleanKeyword = (keyword != null && !keyword.isBlank()) ? keyword.trim() : null;
        return bookingRepository.searchAllBookings(status, paymentStatus, serviceType, cleanKeyword, pageable)
                .map(bookingMapper::toResponse);


    }

    private void validateScheduleAndDuration(BookingRequest request) {
        if (!ALLOWED_DURATIONS.contains(request.durationHours())) {
            throw new BadRequestException("Thời lượng ca làm chỉ hỗ trợ: 2, 4, 6, 8, 12 hoặc 24 giờ");
        }
        if (request.serviceType() == ServiceTypeEnum.STAY_247 && request.durationHours() != 24) {
            throw new BadRequestException("Gói lưu trú 24/7 (STAY_247) bắt buộc có thời lượng 24 giờ");
        }
        if (request.durationHours() < 24) {
            int totalMinutes = request.startTime().getHour() * 60
                    + request.startTime().getMinute()
                    + request.durationHours() * 60;
            if (totalMinutes >= 24 * 60) {
                throw new BadRequestException("Ca làm theo giờ không được kéo dài qua 00:00 ngày hôm sau (hãy chọn gói STAY_247 nếu trực qua đêm)");
            }
        }
        LocalDateTime scheduledStart = LocalDateTime.of(request.workDate(), request.startTime());
        LocalDateTime now = LocalDateTime.now(VN_ZONE);
        if (request.workDate().isEqual(LocalDate.now(VN_ZONE)) && scheduledStart.isBefore(now)) {
            throw new BadRequestException("Giờ bắt đầu ca trong ngày hôm nay phải lớn hơn thời điểm hiện tại");
        }


    }

    private BigDecimal calculateListedPrice(ServiceTypeEnum serviceType, Integer durationHours) {
        if (serviceType == ServiceTypeEnum.STAY_247) {
            return new BigDecimal("1800000.00");
        }
        return switch (durationHours) {
            case 2 -> new BigDecimal("300000.00");
            case 4 -> new BigDecimal("450000.00");
            case 6 -> new BigDecimal("630000.00");
            case 8 -> new BigDecimal("800000.00");
            case 12 -> new BigDecimal("1150000.00");
            case 24 -> new BigDecimal("1800000.00");
            default -> throw new BadRequestException("Thời lượng ca làm không hợp lệ");
        };

    }

    private String generateUniqueBookingCode() {
        String datePart = LocalDateTime.now(VN_ZONE).format(CODE_DATE_FMT);
        String code;
        do{
            int rand = ThreadLocalRandom.current().nextInt(1000, 10000);
            code = "BC" + datePart + "-" + rand;
        } while (bookingRepository.existsByBookingCode(code));
        return code;
    }

    private Booking findBookingOrThrow(Long id) {
        return bookingRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Không tìm thấy đơn đặt lịch với ID: " + id));
    }

    private User findUserOrThrow(String username) {
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new NotFoundException("Không tìm thấy người dùng"));
    }

    private void validateAssignedCaregiver(Booking booking, String username){
        if (booking.getCaregiver() == null
                || !booking.getCaregiver().getUser().getUsername().equals(username)) {
            throw new AccessDeniedException("Bạn không phải chăm sóc viên được phân công cho đơn việc này");
        }
    }

    private void validateViewPermission(Booking booking, User currentUser) {
        if(currentUser.getRole() == UserEnum.ADMIN){
            return;
        }
        if (currentUser.getRole() == UserEnum.CUSTOMER) {
            if (!booking.getCustomer().getId().equals(currentUser.getId())) {
                throw new AccessDeniedException("Bạn không có quyền xem đơn đặt lịch của khách hàng khác");
            }
            return;
        }
        if(currentUser.getRole() == UserEnum.CAREGIVER){
            boolean isPending = booking.getStatus() == StatusBookingEnum.PENDING && booking.getCaregiver() == null;
            boolean isAssignedToMe = booking.getCaregiver() != null
                    && booking.getCaregiver().getUser().getId().equals(currentUser.getId());
            if (!isPending && !isAssignedToMe) {
                throw new AccessDeniedException("Bạn không có quyền xem đơn đặt lịch này");
            }

        }
    }
}
