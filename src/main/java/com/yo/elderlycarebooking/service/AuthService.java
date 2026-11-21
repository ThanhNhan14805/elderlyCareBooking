package com.yo.elderlycarebooking.service;

import com.yo.elderlycarebooking.dto.auth.AuthResponse;
import com.yo.elderlycarebooking.dto.auth.CurrentUserResponse;
import com.yo.elderlycarebooking.dto.auth.LoginRequest;
import com.yo.elderlycarebooking.dto.auth.RefreshTokenRequest;
import com.yo.elderlycarebooking.dto.auth.RegisterRequest;

public interface AuthService {
    AuthResponse login(LoginRequest request);
    AuthResponse register(RegisterRequest request);
    AuthResponse refreshToken(RefreshTokenRequest request);
    void logout(String username);
    CurrentUserResponse me(String username);
}
