package com.yo.elderlycarebooking.dto.careRecipient;

import com.yo.elderlycarebooking.domain.enumm.GenderEnum;
import com.yo.elderlycarebooking.domain.enumm.MobilityEnum;

import java.sql.Timestamp;

public record CareRecipientResponse(
        Long recipientId,
        Long customerId,
        String username,
        String customerName,
        String phoneNumber,
        String email,
        String fullName,
        GenderEnum gender,
        Integer yearOfBirth,
        Integer age,
        MobilityEnum mobilityLevel,
        String medicalNotes,
        String specialNotes,
        Timestamp createdAt
) {
}
