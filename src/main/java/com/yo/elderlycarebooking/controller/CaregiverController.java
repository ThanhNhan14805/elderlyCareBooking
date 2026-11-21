package com.yo.elderlycarebooking.controller;

import com.yo.elderlycarebooking.common.ApiResponse;
import com.yo.elderlycarebooking.dto.caregiver.CaregiverAvailabilityRequest;
import com.yo.elderlycarebooking.dto.caregiver.CaregiverRequest;
import com.yo.elderlycarebooking.dto.caregiver.CaregiverResponse;
import com.yo.elderlycarebooking.service.CaregiverService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

@RestController
@RequestMapping("/api/caregivers")
@RequiredArgsConstructor
public class CaregiverController {

    private final CaregiverService caregiverService;

    /**
     * Tìm kiếm, lọc và phân trang danh sách chăm sóc viên
     */
    @GetMapping
    public ApiResponse<Page<CaregiverResponse>> getAllCaregivers(
            @RequestParam(required = false) Boolean isAvailable,
            @RequestParam(required = false) Integer minExperience,
            @RequestParam(required = false) BigDecimal minRating,
            @RequestParam(required = false) String keyword,
            @PageableDefault(size = 10, sort = "avgRating", direction = Sort.Direction.DESC) Pageable pageable
    ) {
        return ApiResponse.success(
                "Lấy danh sách chăm sóc viên thành công",
                caregiverService.getAllCaregivers(isAvailable, minExperience, minRating, keyword, pageable)
        );
    }
    @GetMapping("/me")
    @PreAuthorize("hasRole('CAREGIVER')")
    public ApiResponse<CaregiverResponse> getMyProfile(Authentication authentication) {
        return ApiResponse.success("Lấy hồ sơ chăm sóc viên thành công", caregiverService.getMyProfile(authentication.getName()));
    }

    @PostMapping("/me")
    @PreAuthorize("hasRole('CAREGIVER')")
    public ApiResponse<CaregiverResponse> createMyProfile(
            Authentication authentication,
            @Valid @RequestBody CaregiverRequest request
    ) {
        return ApiResponse.success("Tạo hồ sơ chăm sóc viên thành công", caregiverService.createMyProfile(authentication.getName(), request));
    }

    @PutMapping("/me")
    @PreAuthorize("hasRole('CAREGIVER')")
    public ApiResponse<CaregiverResponse> updateMyProfile(
            Authentication authentication,
            @Valid @RequestBody CaregiverRequest request
    ) {
        return ApiResponse.success("Cập nhật hồ sơ chăm sóc viên thành công", caregiverService.updateMyProfile(authentication.getName(), request));
    }

    /**
     * Tasker bật / tắt trạng thái sẵn sàng nhận việc
     */
    @PatchMapping("/me/availability")
    @PreAuthorize("hasRole('CAREGIVER')")
    public ApiResponse<CaregiverResponse> updateMyAvailability(
            Authentication authentication,
            @Valid @RequestBody CaregiverAvailabilityRequest request
    ) {
        return ApiResponse.success("Cập nhật trạng thái nhận việc thành công", caregiverService.updateMyAvailability(authentication.getName(), request));
    }

    @GetMapping("/{id}")
    public ApiResponse<CaregiverResponse> getCaregiverById(@PathVariable Long id) {
        return ApiResponse.success("Lấy thông tin chăm sóc viên thành công", caregiverService.getCaregiverById(id));
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ApiResponse<Void> deleteCaregiver(@PathVariable Long id) {
        caregiverService.deleteCaregiver(id);
        return ApiResponse.successMessage("Xoá hồ sơ chăm sóc viên thành công");
    }
}
