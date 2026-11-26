package com.yo.elderlycarebooking.dto.booking;

import com.yo.elderlycarebooking.domain.enumm.LocationEnum;
import com.yo.elderlycarebooking.domain.enumm.ServiceTypeEnum;
import jakarta.validation.constraints.*;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;

public record BookingRequest(
        @NotNull(message = "Hồ sơ người được chăm sóc (recipientId) không được để trống")
        Long recipientId,

        @NotNull(message = "Loại gói dịch vụ không được để trống (HOURLY, STAY_247, MONTHLY_RECURRING)")
        ServiceTypeEnum serviceType,

        @NotNull(message = "Loại địa điểm không được để trống (HOME, HOSPITAL)")
        LocationEnum locationType,

        @NotBlank(message = "Địa chỉ làm việc không được để trống")
        @Size(max = 500, message = "Địa chỉ tối đa 500 ký tự")
        String address,

        @Size(max = 100, message = "Chi tiết địa chỉ tối đa 100 ký tự")
        String addressDetail,

        @NotNull(message = "Thời lượng ca làm không được để trống (2, 4, 6, 8, 12, 24 giờ)")
        Integer durationHours,

        @NotNull(message = "Ngày làm việc không được để trống")
        @FutureOrPresent(message = "Ngày làm việc phải từ hôm nay trở đi")
        LocalDate workDate,

        @NotNull(message = "Giờ bắt đầu ca không được để trống")
        LocalTime startTime,

        Boolean preferFavoriteTasker,

        Boolean isWeeklyRecurring,

        @Size(max = 2000, message = "Ghi chú công việc tối đa 2000 ký tự")
        String workNotes,

        @NotBlank(message = "Tên người liên hệ không được để trống")
        @Size(max = 100, message = "Tên người liên hệ tối đa 100 ký tự")
        String contactName,

        @NotBlank(message = "Số điện thoại liên hệ không được để trống")
        @Pattern(regexp = "^[0-9]{10,11}$", message = "Số điện thoại liên hệ không hợp lệ (10-11 chữ số)")
        String contactPhone,

        @DecimalMin(value = "0.0", message = "Số tiền giảm giá không được âm")
        BigDecimal discountAmount
) {
}
