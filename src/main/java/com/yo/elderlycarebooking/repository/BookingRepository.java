package com.yo.elderlycarebooking.repository;

import com.yo.elderlycarebooking.domain.entity.Booking;
import com.yo.elderlycarebooking.domain.entity.Caregiver;
import com.yo.elderlycarebooking.domain.enumm.PaymentStatusEnum;
import com.yo.elderlycarebooking.domain.enumm.ServiceTypeEnum;
import com.yo.elderlycarebooking.domain.enumm.StatusBookingEnum;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Optional;

@Repository
public interface BookingRepository extends JpaRepository<Booking, Long> {
    @Override
    @EntityGraph(attributePaths = {"customer", "recipient", "caregiver", "caregiver.user"})
    //EntityGraph: tự động nối LEFT JOIN cả 4 đối tượng customer, recipient, caregiver và caregiver.user về ngay trong 1 câu SQL duy nhất!
    Optional<Booking> findById(Long id);

    boolean existsByBookingCode(String bookingCode);

    // 1. Khách hàng xem lịch sử đơn của mình
    @EntityGraph(attributePaths = {"customer", "recipient", "caregiver", "caregiver.user"})
    @Query("""
            SELECT b FROM Booking b
            WHERE b.customer.username = :username
              AND (:status IS NULL OR b.status = :status)
            """)
    Page<Booking> findMyBookings(
            @Param("username") String username,
            @Param("status") StatusBookingEnum status,
            Pageable pageable
    );

    // 2. Chăm sóc viên xem danh sách đơn đang chờ nhận việc (PENDING)
    @EntityGraph(attributePaths = {"customer", "recipient"})
    @Query("""
            SELECT b FROM Booking b
            WHERE b.status = com.yo.elderlycarebooking.domain.enumm.StatusBookingEnum.PENDING
              AND b.caregiver IS NULL
              AND (:serviceType IS NULL OR b.serviceType = :serviceType)
            """)
    Page<Booking> findAvailableBookings(
            @Param("serviceType") ServiceTypeEnum serviceType,
            Pageable pageable
    );

    // 3. Chăm sóc viên xem danh sách ca làm của mình
    @EntityGraph(attributePaths = {"customer", "recipient", "caregiver", "caregiver.user"})
    @Query("""
            SELECT b FROM Booking b
            WHERE b.caregiver.user.username = :username
              AND (:status IS NULL OR b.status = :status)
            """)
    Page<Booking> findCaregiverBookings(
            @Param("username") String username,
            @Param("status") StatusBookingEnum status,
            Pageable pageable
    );

    // 4. Admin tra cứu & lọc toàn bộ đơn trên hệ thống
    @EntityGraph(attributePaths = {"customer", "recipient", "caregiver", "caregiver.user"})
    @Query("""
            SELECT b FROM Booking b
            JOIN b.customer c
            JOIN b.recipient r
            LEFT JOIN b.caregiver cg
            LEFT JOIN cg.user cgu
            WHERE (:status IS NULL OR b.status = :status)
              AND (:paymentStatus IS NULL OR b.paymentStatus = :paymentStatus)
              AND (:serviceType IS NULL OR b.serviceType = :serviceType)
              AND (:keyword IS NULL OR :keyword = ''
                   OR LOWER(b.bookingCode) LIKE LOWER(CONCAT('%', :keyword, '%'))
                   OR LOWER(c.fullName) LIKE LOWER(CONCAT('%', :keyword, '%'))
                   OR LOWER(r.fullName) LIKE LOWER(CONCAT('%', :keyword, '%'))
                   OR LOWER(COALESCE(cgu.fullName, '')) LIKE LOWER(CONCAT('%', :keyword, '%')))
            """)
    Page<Booking> searchAllBookings(
            @Param("status") StatusBookingEnum status,
            @Param("paymentStatus") PaymentStatusEnum paymentStatus,
            @Param("serviceType") ServiceTypeEnum serviceType,
            @Param("keyword") String keyword,
            Pageable pageable
    );

    // 5. Kiểm tra Caregiver có bị trùng lịch ca trực trong cùng ngày không
    @Query("""
            SELECT COUNT(b) > 0 FROM Booking b
            WHERE b.caregiver.id = :caregiverId
              AND b.workDate = :workDate
              AND b.status IN (
                  com.yo.elderlycarebooking.domain.enumm.StatusBookingEnum.ASSIGNED,
                  com.yo.elderlycarebooking.domain.enumm.StatusBookingEnum.IN_PROGRESS
              )
              AND (
                  b.durationHours = 24
                  OR :durationHours = 24
                  OR (b.startTime < :endTime AND b.endTime > :startTime)
              )
            """)
    boolean existsOverlappingSchedule(
            @Param("caregiverId") Long caregiverId,
            @Param("workDate") LocalDate workDate,
            @Param("startTime") LocalTime startTime,
            @Param("endTime") LocalTime endTime,
            @Param("durationHours") Integer durationHours
    );

    // 6. ATOMIC LOCK: Chống nhiều Caregiver bấm nhận cùng 1 đơn tại cùng 1 thời điểm
    @Modifying(clearAutomatically = true, flushAutomatically = true)
    // @Modifying: Báo cho Spring Data JPA biết đây là câu lệnh thay đổi dữ liệu (UPDATE/DELETE) chứ không phải SELECT
    // clearAutomatically: Xóa sạch bộ nhớ tạm của JPA sau khi chạy xong câu UPDATE
    // flushAutomatically: Đẩy toàn bộ các thay đổi đang nằm trong bộ nhớ tạm (Persistence Context / First-level Cache) xuống database trước khi chạy câu UPDATE này, tránh mất dữ liệu chưa kịp lưu
    @Query("""
            UPDATE Booking b
            SET b.caregiver = :caregiver,
                b.status = com.yo.elderlycarebooking.domain.enumm.StatusBookingEnum.ASSIGNED
            WHERE b.id = :bookingId
              AND b.status = com.yo.elderlycarebooking.domain.enumm.StatusBookingEnum.PENDING
              AND b.caregiver IS NULL
            """)
    int acceptBookingAtomically(
            @Param("bookingId") Long bookingId,
            @Param("caregiver") Caregiver caregiver
    );

}
