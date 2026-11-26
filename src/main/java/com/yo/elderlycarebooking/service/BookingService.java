package com.yo.elderlycarebooking.service;

import com.yo.elderlycarebooking.domain.enumm.PaymentStatusEnum;
import com.yo.elderlycarebooking.domain.enumm.ServiceTypeEnum;
import com.yo.elderlycarebooking.domain.enumm.StatusBookingEnum;
import com.yo.elderlycarebooking.dto.booking.BookingRequest;
import com.yo.elderlycarebooking.dto.booking.BookingResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface BookingService {

    BookingResponse createBooking(String username, BookingRequest request);

    Page<BookingResponse> getMyBookings(String username, StatusBookingEnum status, Pageable pageable);

    Page<BookingResponse> getAvailableBookings(ServiceTypeEnum serviceType, Pageable pageable);

    Page<BookingResponse> getCaregiverBookings(String username, StatusBookingEnum status, Pageable pageable);

    BookingResponse getBookingById(Long id, String username);

    BookingResponse acceptBooking(Long id, String username);

    BookingResponse checkIn(Long id, String username);

    BookingResponse checkOut(Long id, String username);

    BookingResponse cancelBooking(Long id, String username);

    Page<BookingResponse> getAllBookingsForAdmin(
            StatusBookingEnum status,
            PaymentStatusEnum paymentStatus,
            ServiceTypeEnum serviceType,
            String keyword,
            Pageable pageable
    );
}
