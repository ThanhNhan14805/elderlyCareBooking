package com.yo.elderlycarebooking.mapper;

import com.yo.elderlycarebooking.domain.entity.Booking;
import com.yo.elderlycarebooking.domain.entity.Payment;
import com.yo.elderlycarebooking.dto.booking.BookingRequest;
import com.yo.elderlycarebooking.dto.booking.BookingResponse;
import com.yo.elderlycarebooking.dto.payment.PaymentResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface BookingMapper {
    // 1. Chuyển Booking (Entity) -> BookingResponse (DTO)
    @Mapping(source = "id", target = "bookingId")
    @Mapping(source = "customer.id", target = "customerId")
    @Mapping(source = "customer.fullName", target = "customerName")
    @Mapping(source = "customer.phoneNumber", target = "customerPhone")
    @Mapping(source = "recipient.id", target = "recipientId")
    @Mapping(source = "recipient.fullName", target = "recipientName")
    @Mapping(source = "caregiver.id", target = "caregiverId")
    @Mapping(source = "caregiver.user.fullName", target = "caregiverName")
    @Mapping(source = "caregiver.user.phoneNumber", target = "caregiverPhone")
    BookingResponse toResponse(Booking booking);

    // 2. Chuyển BookingRequest (DTO) -> Booking (Entity) khi khởi tạo đơn
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "bookingCode", ignore = true)
    @Mapping(target = "customer", ignore = true)
    @Mapping(target = "recipient", ignore = true)
    @Mapping(target = "caregiver", ignore = true)
    @Mapping(target = "endTime", ignore = true)
    @Mapping(target = "servicePrice", ignore = true)
    @Mapping(target = "totalPrice", ignore = true)
    @Mapping(target = "paymentStatus", constant = "UNPAID")
    @Mapping(target = "status", constant = "PENDING")
    @Mapping(target = "checkInTime", ignore = true)
    @Mapping(target = "checkOutTime", ignore = true)
    @Mapping(target = "preferFavoriteTasker", expression = "java(request.preferFavoriteTasker() != null ? request.preferFavoriteTasker() : false)")
    @Mapping(target = "isWeeklyRecurring", expression = "java(request.isWeeklyRecurring() != null ? request.isWeeklyRecurring() : false)")
    @Mapping(target = "discountAmount", expression = "java(request.discountAmount() != null ? request.discountAmount() : java.math.BigDecimal.ZERO)")
    @Mapping(target = "createdAt", expression = "java(java.sql.Timestamp.from(java.time.Instant.now()))")
    Booking toEntity(BookingRequest request);

}
