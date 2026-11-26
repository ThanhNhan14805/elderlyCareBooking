package com.yo.elderlycarebooking.dto.booking;

import com.yo.elderlycarebooking.domain.enumm.LocationEnum;
import com.yo.elderlycarebooking.domain.enumm.PaymentStatusEnum;
import com.yo.elderlycarebooking.domain.enumm.ServiceTypeEnum;
import com.yo.elderlycarebooking.domain.enumm.StatusBookingEnum;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalTime;

public record BookingResponse (
    Long bookingId,
    String bookingCode,
    Long customerId,
    String customerName,
    String customerPhone,
    Long recipientId,
    String recipientName,
    Long caregiverId,
    String caregiverName,
    String caregiverPhone,
    ServiceTypeEnum serviceType,
    LocationEnum locationType,
    String address,
    String addressDetail,
    Integer durationHours,
    LocalDate workDate,
    LocalTime startTime,
    LocalTime endTime,
    Boolean preferFavoriteTasker,
    Boolean isWeeklyRecurring,
    String workNotes,
    String contactName,
    String contactPhone,
    BigDecimal servicePrice,
    BigDecimal discountAmount,
    BigDecimal totalPrice,
    PaymentStatusEnum paymentStatus,
    StatusBookingEnum status,
    Timestamp checkInTime,
    Timestamp checkOutTime,
    Timestamp createdAt
){

}
