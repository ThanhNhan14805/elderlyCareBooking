package com.yo.elderlycarebooking.dto.careRecipient;

import com.yo.elderlycarebooking.domain.enumm.GenderEnum;
import com.yo.elderlycarebooking.domain.enumm.MobilityEnum;
import jakarta.validation.constraints.*;


public record CareRecipientRequest(

        @NotBlank @Size(max = 100) String fullName,

        @NotNull GenderEnum gender,

        @NotNull @Min(1900) @Max(2026) Integer yearOfBirth,

        MobilityEnum mobilityLevel,

        @Size(max = 2000) String medicalNotes,

        @Size(max = 2000) String specialNotes
) {
}
