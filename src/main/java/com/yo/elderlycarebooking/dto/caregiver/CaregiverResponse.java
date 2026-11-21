package com.yo.elderlycarebooking.dto.caregiver;

import java.math.BigDecimal;
import java.sql.Timestamp;

public record CaregiverResponse(
        Long caregiverId,
        Long userId,
        String username,
        String fullName,
        String phoneNumber,
        String email,
        Integer experienceYears,
        String qualification,
        String bio,
        Boolean isAvailable,
        BigDecimal avgRating,
        Integer totalJobsDone,
        Timestamp createdAt
) {
}
