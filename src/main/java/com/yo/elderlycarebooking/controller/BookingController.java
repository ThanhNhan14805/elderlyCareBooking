package com.yo.elderlycarebooking.controller;


import com.yo.elderlycarebooking.common.ApiResponse;
import com.yo.elderlycarebooking.domain.enumm.PaymentStatusEnum;
import com.yo.elderlycarebooking.domain.enumm.ServiceTypeEnum;
import com.yo.elderlycarebooking.domain.enumm.StatusBookingEnum;
import com.yo.elderlycarebooking.dto.booking.BookingRequest;
import com.yo.elderlycarebooking.dto.booking.BookingResponse;
import com.yo.elderlycarebooking.service.BookingService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/bookings")
@RequiredArgsConstructor
public class BookingController {

    private final BookingService bookingService;

    @PostMapping
    @PreAuthorize("hasRole('CUSTOMER')")
    public ApiResponse<BookingResponse> createBooking(
            Authentication authentication,
            @Valid @RequestBody BookingRequest request
    ) {
        return ApiResponse.success(
                "Khởi tạo đơn đặt lịch thành công",
                bookingService.createBooking(authentication.getName(), request)
        );
    }

    @GetMapping("/my")
    @PreAuthorize("hasRole('CUSTOMER')")
    public ApiResponse<Page<BookingResponse>> getMyBookings(
            Authentication authentication,
            @RequestParam(required = false) StatusBookingEnum status,
            @PageableDefault(size = 10, sort = "createdAt", direction = Sort.Direction.DESC) Pageable pageable
    ) {
        return ApiResponse.success(
                "Lấy danh sách đơn đặt lịch của bạn thành công",
                bookingService.getMyBookings(authentication.getName(), status, pageable)
        );
    }

    /**
     * Chăm sóc viên xem danh sách các đơn việc đang chờ (PENDING)
     */
    @GetMapping("/available")
    @PreAuthorize("hasRole('CAREGIVER')")
    public ApiResponse<Page<BookingResponse>> getAvailableBookings(
            @RequestParam(required = false) ServiceTypeEnum serviceType,
            @PageableDefault(size = 10, sort = "workDate", direction = Sort.Direction.ASC) Pageable pageable
    ) {
        return ApiResponse.success(
                "Lấy danh sách đơn việc đang chờ thành công",
                bookingService.getAvailableBookings(serviceType, pageable)
        );
    }

    /**
     * Chăm sóc viên xem danh sách ca làm đã nhận / đang làm / hoàn thành của mình
     */
    @GetMapping("/assigned")
    @PreAuthorize("hasRole('CAREGIVER')")
    public ApiResponse<Page<BookingResponse>> getCaregiverBookings(
            Authentication authentication,
            @RequestParam(required = false) StatusBookingEnum status,
            @PageableDefault(size = 10, sort = "workDate", direction = Sort.Direction.DESC) Pageable pageable
    ) {
        return ApiResponse.success(
                "Lấy danh sách ca làm việc của bạn thành công",
                bookingService.getCaregiverBookings(authentication.getName(), status, pageable)
        );
    }

    @GetMapping("/{id}")
    public ApiResponse<BookingResponse> getBookingById(
            @PathVariable Long id,
            Authentication authentication
    ) {
        return ApiResponse.success(
                "Lấy chi tiết đơn đặt lịch thành công",
                bookingService.getBookingById(id, authentication.getName())
        );
    }

    /**
     * Chăm sóc viên nhận đơn (sử dụng Atomic Lock chống nhận trùng)
     */
    @PostMapping("/{id}/accept")
    @PreAuthorize("hasRole('CAREGIVER')")
    public ApiResponse<BookingResponse> acceptBooking(
            @PathVariable Long id,
            Authentication authentication
    ) {
        return ApiResponse.success(
                "Tiếp nhận đơn việc thành công",
                bookingService.acceptBooking(id, authentication.getName())
        );
    }

    /**
     * Chăm sóc viên chấm công bắt đầu ca (trong khoảng [-15 phút, +30 phút] so với startTime)
     */
    @PostMapping("/{id}/check-in")
    @PreAuthorize("hasRole('CAREGIVER')")
    public ApiResponse<BookingResponse> checkIn(
            @PathVariable Long id,
            Authentication authentication
    ) {
        return ApiResponse.success(
                "Chấm công bắt đầu ca làm việc thành công",
                bookingService.checkIn(id, authentication.getName())
        );
    }

    @PostMapping("/{id}/check-out")
    @PreAuthorize("hasRole('CAREGIVER')")
    public ApiResponse<BookingResponse> checkOut(
            @PathVariable Long id,
            Authentication authentication
    ) {
        return ApiResponse.success(
                "Hoàn thành ca làm việc thành công",
                bookingService.checkOut(id, authentication.getName())
        );
    }

    /**
     * Khách hàng hoặc Admin huỷ đơn (khi đang PENDING hoặc ASSIGNED)
     */
    @PostMapping("/{id}/cancel")
    @PreAuthorize("hasAnyRole('CUSTOMER', 'ADMIN')")
    public ApiResponse<BookingResponse> cancelBooking(
            @PathVariable Long id,
            Authentication authentication
    ) {
        return ApiResponse.success(
                "Huỷ đơn đặt lịch thành công",
                bookingService.cancelBooking(id, authentication.getName())
        );
    }

    /**
     * Admin tra cứu, lọc và phân trang toàn bộ đơn đặt lịch trên hệ thống
     */
    @GetMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ApiResponse<Page<BookingResponse>> getAllBookingsForAdmin(
            @RequestParam(required = false) StatusBookingEnum status,
            @RequestParam(required = false) PaymentStatusEnum paymentStatus,
            @RequestParam(required = false) ServiceTypeEnum serviceType,
            @RequestParam(required = false) String keyword,
            @PageableDefault(size = 10, sort = "createdAt", direction = Sort.Direction.DESC) Pageable pageable
    ) {
        return ApiResponse.success(
                "Lấy danh sách toàn bộ đơn đặt lịch thành công",
                bookingService.getAllBookingsForAdmin(status, paymentStatus, serviceType, keyword, pageable)
        );
    }
}


