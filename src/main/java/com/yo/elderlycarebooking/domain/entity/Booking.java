package com.yo.elderlycarebooking.domain.entity;

import com.yo.elderlycarebooking.domain.enumm.LocationEnum;
import com.yo.elderlycarebooking.domain.enumm.PaymentStatusEnum;
import com.yo.elderlycarebooking.domain.enumm.ServiceTypeEnum;
import com.yo.elderlycarebooking.domain.enumm.StatusBookingEnum;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Builder.Default;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Table(name = "bookings")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Booking {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "booking_id")
    private Long id;

    @Column(name = "booking_code", nullable = false, unique = true, length = 20)
    private String bookingCode;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_id", nullable = false)
    private User customer;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "recipient_id", nullable = false)
    private CareRecipient recipient;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "caregiver_id")
    private Caregiver caregiver;

    @Default
    @Enumerated(EnumType.STRING)
    @Column(name = "service_type", nullable = false, length = 30)
    private ServiceTypeEnum serviceType = ServiceTypeEnum.HOURLY;

    @Default
    @Enumerated(EnumType.STRING)
    @Column(name = "location_type", nullable = false, length = 20)
    private LocationEnum locationType = LocationEnum.HOME;

    @Column(name = "address", nullable = false, columnDefinition = "TEXT")
    private String address;

    @Column(name = "address_detail", length = 100)
    private String addressDetail;

    @Default
    @Column(name = "duration_hours", nullable = false)
    private Integer durationHours = 2;

    @Column(name = "work_date", nullable = false)
    private LocalDate workDate;

    @Column(name = "start_time", nullable = false)
    private LocalTime startTime;

    @Column(name = "end_time", nullable = false)
    private LocalTime endTime;

    @Default
    @Column(name = "prefer_favorite_tasker")
    private Boolean preferFavoriteTasker = false;

    @Default
    @Column(name = "is_weekly_recurring")
    private Boolean isWeeklyRecurring = false;

    @Column(name = "work_notes", columnDefinition = "TEXT")
    private String workNotes;

    @Column(name = "contact_name", nullable = false, length = 100)
    private String contactName;

    @Column(name = "contact_phone", nullable = false, length = 15)
    private String contactPhone;

    @Column(name = "service_price", nullable = false, precision = 12, scale = 2)
    private BigDecimal servicePrice;

    @Default
    @Column(name = "discount_amount", precision = 12, scale = 2)
    private BigDecimal discountAmount = BigDecimal.ZERO;

    @Column(name = "total_price", nullable = false, precision = 12, scale = 2)
    private BigDecimal totalPrice;

    @Default
    @Enumerated(EnumType.STRING)
    @Column(name = "payment_status", nullable = false, length = 20)
    private PaymentStatusEnum paymentStatus = PaymentStatusEnum.UNPAID;

    @Default
    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false, length = 20)
    private StatusBookingEnum status = StatusBookingEnum.PENDING;

    @Column(name = "check_in_time")
    private Timestamp checkInTime;

    @Column(name = "check_out_time")
    private Timestamp checkOutTime;

    @Column(name = "created_at", updatable = false)
    private Timestamp createdAt;
}
