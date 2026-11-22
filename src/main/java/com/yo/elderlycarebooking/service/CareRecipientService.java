package com.yo.elderlycarebooking.service;

import com.yo.elderlycarebooking.domain.enumm.GenderEnum;
import com.yo.elderlycarebooking.domain.enumm.MobilityEnum;
import com.yo.elderlycarebooking.dto.careRecipient.CareRecipientRequest;
import com.yo.elderlycarebooking.dto.careRecipient.CareRecipientResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface CareRecipientService {
    List<CareRecipientResponse> getMyRecipients(String username);

    CareRecipientResponse createRecipient(String username, CareRecipientRequest request);

    CareRecipientResponse getRecipientById(Long id, String username);

    CareRecipientResponse updateRecipient(Long id, String username, CareRecipientRequest request);

    void deleteRecipient(Long id, String username);

    Page<CareRecipientResponse> getAllRecipientsForAdmin(
            Long customerId,
            MobilityEnum mobilityLevel,
            GenderEnum gender,
            String keyword,
            Pageable pageable
    );
}
