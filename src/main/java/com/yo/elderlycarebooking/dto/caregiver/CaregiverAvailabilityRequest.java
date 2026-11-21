package com.yo.elderlycarebooking.dto.caregiver;

import jakarta.validation.constraints.NotNull;

// Trường hợp: dùng để bật tắt trạng thái nhận chăm sóc của Caregiver
// Nếu để cùng với caregiversRequest thì mỗi lần bật tắt sẽ gửi lại toàn bộ dữ liệu
public record CaregiverAvailabilityRequest(
        @NotNull(message = "Trạng thái sẵn sàng nhận việc không được để trống")
        Boolean isAvailable
) {
}
