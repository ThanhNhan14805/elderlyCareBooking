package com.yo.elderlycarebooking.dto.caregiver;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record CaregiverRequest(
        @NotNull(message = "Số năm kinh nghiệm không được để trống")
        @Min(value = 0, message = "Số năm kinh nghiệm tối thiểu là 0")
        @Max(value = 50, message = "Số năm kinh nghiệm tối đa là 50")
        Integer experienceYears,

        @NotBlank(message = "Bằng cấp / Chứng chỉ chuyên môn không được để trống")
        @Size(max = 100, message = "Bằng cấp / Chứng chỉ tối đa 100 ký tự")
        String qualification,

        @Size( message = "Giới thiệu bản thân ")
        String bio,

        Boolean isAvailable
) {
}
