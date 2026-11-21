package com.yo.elderlycarebooking.service.Impl;

import com.yo.elderlycarebooking.common.exception.BadRequestException;
import com.yo.elderlycarebooking.common.exception.ConflictException;
import com.yo.elderlycarebooking.common.exception.NotFoundException;
import com.yo.elderlycarebooking.domain.entity.Caregiver;
import com.yo.elderlycarebooking.domain.entity.User;
import com.yo.elderlycarebooking.domain.enumm.UserEnum;
import com.yo.elderlycarebooking.dto.caregiver.CaregiverAvailabilityRequest;
import com.yo.elderlycarebooking.dto.caregiver.CaregiverRequest;
import com.yo.elderlycarebooking.dto.caregiver.CaregiverResponse;
import com.yo.elderlycarebooking.mapper.CaregiverMapper;
import com.yo.elderlycarebooking.repository.CaregiverRepository;
import com.yo.elderlycarebooking.repository.auth.AuthUserRepository;
import com.yo.elderlycarebooking.service.CaregiverService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

@Service
@RequiredArgsConstructor
public class CaregiverServiceImpl implements CaregiverService {

    private final CaregiverRepository caregiverRepository;
    private final AuthUserRepository authUserRepository;
    private final CaregiverMapper caregiverMapper;

    @Override
    @Transactional(readOnly = true)
    public Page<CaregiverResponse> getAllCaregivers(
            Boolean isAvailable,
            Integer minExperience,
            BigDecimal minRating,
            String keyword,
            Pageable pageable
    ) {
        String cleanKeyword = (keyword != null && !keyword.isBlank()) ? keyword.trim() : null;
        return caregiverRepository.searchCaregivers(isAvailable, minExperience, minRating, cleanKeyword, pageable)
                .map(caregiverMapper::toResponse);
    }

    @Override
    @Transactional(readOnly = true)
    public CaregiverResponse getCaregiverById(Long id) {
        Caregiver caregiver = caregiverRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Không tìm thấy hồ sơ chăm sóc viên với ID: " + id));
        return caregiverMapper.toResponse(caregiver);
    }

    @Override
    @Transactional(readOnly = true)
    public CaregiverResponse getMyProfile(String username) {
        Caregiver caregiver = caregiverRepository.findByUserUsername(username)
                .orElseThrow(() -> new NotFoundException("Bạn chưa khởi tạo hồ sơ chăm sóc viên"));
        return caregiverMapper.toResponse(caregiver);
    }

    @Override
    @Transactional
    public CaregiverResponse createMyProfile(String username, CaregiverRequest request) {
        User user = authUserRepository.findByUsername(username)
                .orElseThrow(() -> new NotFoundException("Không tìm thấy người dùng"));

        if (user.getRole() != UserEnum.CAREGIVER) {
            throw new BadRequestException("Tài khoản của bạn không có vai trò CAREGIVER");
        }

        if (caregiverRepository.existsByUserId(user.getId())) {
            throw new ConflictException("Hồ sơ chăm sóc viên của bạn đã tồn tại");
        }
// ----------------- THỦ CÔNG ---------------------------
//        BƯỚC 1: Dùng Builder để tạo đối tượng Entity (Caregiver) từ Request của Client
//        Caregiver caregiver = Caregiver.builder()
//                .user(user)
//                .experienceYears(request.experienceYears())
//                .qualification(request.qualification())
//                .bio(request.bio())
//                .isAvailable(request.isAvailable() != null ? request.isAvailable() : true)
//                .avgRating(new BigDecimal("5.00"))
//                .totalJobsDone(0)
//                .createdAt(Timestamp.from(Instant.now()))
//                .build();
//        BƯỚC 2: Lưu Entity xuống DB rồi dùng toResponse() để đổi sang DTO trả về Client
//        return toResponse(caregiverRepository.save(caregiver));
        Caregiver caregiver = caregiverMapper.toEntity(request);
        caregiver.setUser(user);

        return caregiverMapper.toResponse(caregiverRepository.save(caregiver));
    }

    @Override
    @Transactional
    public CaregiverResponse updateMyProfile(String username, CaregiverRequest request) {
        Caregiver caregiver = caregiverRepository.findByUserUsername(username)
                .orElseThrow(() -> new NotFoundException("Bạn chưa khởi tạo hồ sơ chăm sóc viên"));

        caregiverMapper.updateEntityFromRequest(request, caregiver);

        return caregiverMapper.toResponse(caregiverRepository.save(caregiver));
    }

    @Override
    @Transactional
    public CaregiverResponse updateMyAvailability(String username, CaregiverAvailabilityRequest request) {
        Caregiver caregiver = caregiverRepository.findByUserUsername(username)
                .orElseThrow(() -> new NotFoundException("Bạn chưa khởi tạo hồ sơ chăm sóc viên"));

        caregiver.setIsAvailable(request.isAvailable());
        return caregiverMapper.toResponse(caregiverRepository.save(caregiver));
    }

    @Override
    @Transactional
    public void deleteCaregiver(Long id) {
        Caregiver caregiver = caregiverRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Không tìm thấy hồ sơ chăm sóc viên với ID: " + id));
        caregiverRepository.delete(caregiver);
    }
}
