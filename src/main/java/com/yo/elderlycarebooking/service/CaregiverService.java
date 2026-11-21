package com.yo.elderlycarebooking.service;

import com.yo.elderlycarebooking.dto.caregiver.CaregiverAvailabilityRequest;
import com.yo.elderlycarebooking.dto.caregiver.CaregiverRequest;
import com.yo.elderlycarebooking.dto.caregiver.CaregiverResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.math.BigDecimal;

public interface CaregiverService {

    Page<CaregiverResponse> getAllCaregivers(
            Boolean isAvailable,
            Integer minExperience,
            BigDecimal minRating,
            String keyword,
            Pageable pageable
    );

    CaregiverResponse getCaregiverById(Long id);

    CaregiverResponse getMyProfile(String username);

    CaregiverResponse createMyProfile(String username, CaregiverRequest request);

    CaregiverResponse updateMyProfile(String username, CaregiverRequest request);

    CaregiverResponse updateMyAvailability(String username, CaregiverAvailabilityRequest request);

    void deleteCaregiver(Long id);
}
