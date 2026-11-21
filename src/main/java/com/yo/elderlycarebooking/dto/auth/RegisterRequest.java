package com.yo.elderlycarebooking.dto.auth;

import com.yo.elderlycarebooking.domain.enumm.UserEnum;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record RegisterRequest(
        @NotBlank(message = "Username không được để trống")
        @Size(min = 4, max = 50, message = "Username từ 4-50 ký tự")
        @Pattern(regexp = "^[a-zA-Z0-9_]+$", message = "Username chỉ gồm chữ cái, số và dấu _")
        String username,

        @NotBlank(message = "Số điện thoại không được để trống")
        @Pattern(regexp = "^[0-9]{10,11}$", message = "Số điện thoại không hợp lệ")
        String phoneNumber,

        @NotBlank(message = "Mật khẩu không được để trống")
        @Size(min = 6, message = "Mật khẩu tối thiểu 6 ký tự")
        String password,

        @NotBlank(message = "Họ tên không được để trống")
        String fullName,

        @Email(message = "Email không đúng định dạng")
        String email,

        // Mặc định CUSTOMER nếu không truyền
        UserEnum role
) {
}
