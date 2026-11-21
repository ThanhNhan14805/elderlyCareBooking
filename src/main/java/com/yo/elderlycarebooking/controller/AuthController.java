package com.yo.elderlycarebooking.controller;

import com.yo.elderlycarebooking.common.ApiResponse;
import com.yo.elderlycarebooking.dto.auth.AuthResponse;
import com.yo.elderlycarebooking.dto.auth.CurrentUserResponse;
import com.yo.elderlycarebooking.dto.auth.LoginRequest;
import com.yo.elderlycarebooking.dto.auth.RefreshTokenRequest;
import com.yo.elderlycarebooking.dto.auth.RegisterRequest;
import com.yo.elderlycarebooking.service.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;


    @PostMapping("/register")
    public ApiResponse<AuthResponse> register(@Valid @RequestBody RegisterRequest request) {
        return ApiResponse.success("Đăng ký thành công", authService.register(request));
    }


    @PostMapping("/login")
    public ApiResponse<AuthResponse> login(@Valid @RequestBody LoginRequest request) {
        return ApiResponse.success("Đăng nhập thành công", authService.login(request));
    }

    /**
     * POST /api/auth/refresh
     * Body: { "refreshToken": "..." }
     */
    @PostMapping("/refresh")
    public ApiResponse<AuthResponse> refresh(@Valid @RequestBody RefreshTokenRequest request) {
        return ApiResponse.success("Làm mới token thành công", authService.refreshToken(request));
    }

    @PostMapping("/logout")
    public ApiResponse<Void> logout(Authentication authentication) {
        authService.logout(authentication.getName());
        return ApiResponse.successMessage("Đăng xuất thành công");
    }

    @GetMapping("/me")
    public ApiResponse<CurrentUserResponse> me(Authentication authentication) {
        return ApiResponse.success(authService.me(authentication.getName()));
    }
}
