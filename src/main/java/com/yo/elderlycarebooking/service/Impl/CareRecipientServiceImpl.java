package com.yo.elderlycarebooking.service.Impl;

import com.yo.elderlycarebooking.common.exception.NotFoundException;
import com.yo.elderlycarebooking.domain.entity.CareRecipient;
import com.yo.elderlycarebooking.domain.entity.User;
import com.yo.elderlycarebooking.domain.enumm.GenderEnum;
import com.yo.elderlycarebooking.domain.enumm.MobilityEnum;
import com.yo.elderlycarebooking.domain.enumm.UserEnum;
import com.yo.elderlycarebooking.dto.careRecipient.CareRecipientRequest;
import com.yo.elderlycarebooking.dto.careRecipient.CareRecipientResponse;
import com.yo.elderlycarebooking.dto.caregiver.CaregiverAvailabilityRequest;
import com.yo.elderlycarebooking.dto.caregiver.CaregiverRequest;
import com.yo.elderlycarebooking.dto.caregiver.CaregiverResponse;
import com.yo.elderlycarebooking.mapper.CareRecipientMapper;
import com.yo.elderlycarebooking.repository.CareRecipientRepository;
import com.yo.elderlycarebooking.repository.auth.AuthUserRepository;
import com.yo.elderlycarebooking.service.CareRecipientService;
import com.yo.elderlycarebooking.service.CaregiverService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CareRecipientServiceImpl implements CareRecipientService {

    private final CareRecipientRepository careRecipientRepository;
    private final AuthUserRepository authUserRepository;
    private final CareRecipientMapper careRecipientMapper;


    @Override
    @Transactional
    public List<CareRecipientResponse> getMyRecipients(String username) {
        // return careRecipientRepository.findAllByCustomerUsernameOrderByCreatedAtDesc(username)
        //        .stream()
        //        .map(recipient -> careRecipientMapper.toResponse(recipient))
        //        .toList();
        return careRecipientRepository.findAllByCustomerUsernameOrderByCreatedAtDesc(username)
                .stream()
                .map(careRecipientMapper::toResponse)
                .toList();
    }

    @Override
    @Transactional
    public CareRecipientResponse createRecipient(String username, CareRecipientRequest request) {
        User customer = authUserRepository.findByUsername(username)
                .orElseThrow(() -> new NotFoundException("Không tìm thấy người dùng"));

        CareRecipient recipient = careRecipientMapper.toEntity(request);
        recipient.setCustomer(customer);

        return careRecipientMapper.toResponse(careRecipientRepository.save(recipient));

    }

    @Override
    @Transactional
    public CareRecipientResponse getRecipientById(Long id, String username) {
        User currentUser = authUserRepository.findByUsername(username)
                .orElseThrow(() -> new NotFoundException("Không tìm thấy người dùng"));

        CareRecipient recipient = careRecipientRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Không tìm thấy hồ sơ người cao tuổi với ID: " + id));

        if (currentUser.getRole() == UserEnum.CUSTOMER
                && !recipient.getCustomer().getId().equals(currentUser.getId())) {
            throw new AccessDeniedException("Bạn không có quyền xem hồ sơ người thân của khách hàng khác");
        }

        return careRecipientMapper.toResponse(recipient);



    }

    @Override
    @Transactional
    public CareRecipientResponse updateRecipient(Long id, String username, CareRecipientRequest request) {

        User currentUser = authUserRepository.findByUsername(username)
                .orElseThrow(() -> new NotFoundException("Không tìm thấy người dùng"));

        CareRecipient recipient = careRecipientRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Không tìm thấy hồ sơ người cao tuổi với ID: " + id));

        if (currentUser.getRole() == UserEnum.CUSTOMER
                && !recipient.getCustomer().getId().equals(currentUser.getId())) {
            throw new AccessDeniedException("Bạn không có quyền cập nhật hồ sơ người thân của khách hàng khác");
        }

        careRecipientMapper.updateEntityFromRequest(request, recipient);

        return careRecipientMapper.toResponse(careRecipientRepository.save(recipient));

    }

    @Override
    @Transactional
    public void deleteRecipient(Long id, String username) {
        User currentUser = authUserRepository.findByUsername(username)
                .orElseThrow(() -> new NotFoundException("Không tìm thấy người dùng"));

        CareRecipient recipient = careRecipientRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Không tìm thấy hồ sơ người cao tuổi với ID: " + id));

        // Chỉ chủ sở hữu (CUSTOMER) hoặc ADMIN mới được xoá
        if (currentUser.getRole() != UserEnum.ADMIN
                && !recipient.getCustomer().getId().equals(currentUser.getId())) {
            throw new AccessDeniedException("Bạn không có quyền xoá hồ sơ người thân của khách hàng khác");
        }

        careRecipientRepository.delete(recipient);
    }

    @Override
    @Transactional
    public Page<CareRecipientResponse> getAllRecipientsForAdmin(Long customerId, MobilityEnum mobilityLevel, GenderEnum gender, String keyword, Pageable pageable) {
        String cleanKeyword = (keyword != null && !keyword.isBlank()) ? keyword.trim() : null;
//        return careRecipientRepository.searchCareRecipients(customerId, mobilityLevel, gender, cleanKeyword, pageable)
//                .map(careRecipientMapper::toResponse);
        return careRecipientRepository.searchCareRecipients(customerId, mobilityLevel, gender, cleanKeyword, pageable)
                .map(recipient -> careRecipientMapper.toResponse(recipient));

    }
}
