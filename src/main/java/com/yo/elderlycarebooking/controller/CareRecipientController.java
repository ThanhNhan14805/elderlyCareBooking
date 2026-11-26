package com.yo.elderlycarebooking.controller;

import com.yo.elderlycarebooking.common.ApiResponse;
import com.yo.elderlycarebooking.domain.enumm.GenderEnum;
import com.yo.elderlycarebooking.domain.enumm.MobilityEnum;
import com.yo.elderlycarebooking.dto.careRecipient.CareRecipientRequest;
import com.yo.elderlycarebooking.dto.careRecipient.CareRecipientResponse;
import com.yo.elderlycarebooking.service.CareRecipientService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/care-recipients")
@RequiredArgsConstructor
public class CareRecipientController {

    private final CareRecipientService careRecipientService;


    @GetMapping("/my")
    @PreAuthorize("hasRole('CUSTOMER')")
    public ApiResponse<List<CareRecipientResponse>> getMyRecipients(Authentication authentication){
        return ApiResponse.success(
                "Lấy danh sách hồ sơ người thân thành công",
                careRecipientService.getMyRecipients(authentication.getName())
        );

    }
    @PostMapping
    @PreAuthorize("hasRole('CUSTOMER')")
    public ApiResponse<CareRecipientResponse> createRecipient(
            Authentication authentication,
            @Valid @RequestBody CareRecipientRequest request
    ) {
        return ApiResponse.success(
                "Tạo hồ sơ người thân thành công",
                careRecipientService.createRecipient(authentication.getName(), request)
        );
    }
    @GetMapping("/{id}")
    public ApiResponse<CareRecipientResponse> getRecipientById(
            @PathVariable Long id,
            Authentication authentication
    ) {
        return ApiResponse.success(
                "Lấy chi tiết hồ sơ người thân thành công",
                careRecipientService.getRecipientById(id, authentication.getName())
        );
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('CUSTOMER')")
    public ApiResponse<CareRecipientResponse> updateRecipient(
            @PathVariable Long id,
            Authentication authentication,
            @Valid @RequestBody CareRecipientRequest request
    ) {
        return ApiResponse.success(
                "Cập nhật hồ sơ người thân thành công",
                careRecipientService.updateRecipient(id, authentication.getName(), request)
        );
    }


    @DeleteMapping("/{id}")
    @PreAuthorize("hasAnyRole('CUSTOMER', 'ADMIN')")
    public ApiResponse<Void> deleteRecipient(
            @PathVariable Long id,
            Authentication authentication
    ) {
        careRecipientService.deleteRecipient(id, authentication.getName());
        return ApiResponse.successMessage("Xoá hồ sơ người thân thành công");
    }

    /**
     * Admin tra cứu, lọc và phân trang toàn bộ hồ sơ người cao tuổi trên hệ thống
     */
    @GetMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ApiResponse<Page<CareRecipientResponse>> getAllRecipientsForAdmin(
            @RequestParam(required = false) Long customerId,
            @RequestParam(required = false) MobilityEnum mobilityLevel,
            @RequestParam(required = false) GenderEnum gender,
            @RequestParam(required = false) String keyword,
            @PageableDefault(size = 10, sort = "createdAt", direction = Sort.Direction.DESC) Pageable pageable
    ) {
        return ApiResponse.success(
                "Lấy danh sách hồ sơ người cao tuổi thành công",
                careRecipientService.getAllRecipientsForAdmin(customerId, mobilityLevel, gender, keyword, pageable)
        );
    }



}
