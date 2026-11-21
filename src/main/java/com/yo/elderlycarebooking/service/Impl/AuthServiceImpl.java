package com.yo.elderlycarebooking.service.Impl;

import com.yo.elderlycarebooking.common.exception.BadRequestException;
import com.yo.elderlycarebooking.common.exception.ConflictException;
import com.yo.elderlycarebooking.common.exception.NotFoundException;
import com.yo.elderlycarebooking.config.AppJwtProperties;
import com.yo.elderlycarebooking.domain.entity.User;
import com.yo.elderlycarebooking.domain.entity.UserRefreshToken;
import com.yo.elderlycarebooking.domain.enumm.UserEnum;
import com.yo.elderlycarebooking.dto.auth.AuthResponse;
import com.yo.elderlycarebooking.dto.auth.CurrentUserResponse;
import com.yo.elderlycarebooking.dto.auth.LoginRequest;
import com.yo.elderlycarebooking.dto.auth.RefreshTokenRequest;
import com.yo.elderlycarebooking.dto.auth.RegisterRequest;
import com.yo.elderlycarebooking.repository.auth.AuthUserRepository;
import com.yo.elderlycarebooking.security.jwt.JwtService;
import com.yo.elderlycarebooking.security.service.RefreshTokenService;
import com.yo.elderlycarebooking.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.time.Instant;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final AuthUserRepository authUserRepository;
    private final RefreshTokenService refreshTokenService;
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;
    private final AppJwtProperties jwtProperties;

    @Override
    @Transactional
    public AuthResponse login(LoginRequest request) {
        User user = authUserRepository.findByUsername(request.username())
                .orElseThrow(() -> new NotFoundException("Username chưa được đăng ký"));

        if (!passwordEncoder.matches(request.password(), user.getPassword())) {
            throw new BadCredentialsException("Username hoặc mật khẩu không chính xác");
        }

        String accessToken = jwtService.generateToken(user);
        String refreshToken = refreshTokenService.createRefreshToken(user.getId()).getTokenHash();

        return buildAuthResponse(user, accessToken, refreshToken);
    }

    @Override
    @Transactional
    public AuthResponse register(RegisterRequest request) {
        if (authUserRepository.existsByUsername(request.username())) {
            throw new ConflictException("Username '" + request.username() + "' đã được sử dụng");
        }

        if (authUserRepository.existsByPhoneNumber(request.phoneNumber())) {
            throw new ConflictException("Số điện thoại " + request.phoneNumber() + " đã được đăng ký");
        }

        User user = User.builder()
                .username(request.username())
                .phoneNumber(request.phoneNumber())
                .password(passwordEncoder.encode(request.password()))
                .fullName(request.fullName())
                .email(request.email())
                .role(request.role() != null ? request.role() : UserEnum.CUSTOMER)
                .createdAt(Timestamp.from(Instant.now()))
                .build();

        user = authUserRepository.save(user);

        String accessToken = jwtService.generateToken(user);
        String refreshToken = refreshTokenService.createRefreshToken(user.getId()).getTokenHash();

        return buildAuthResponse(user, accessToken, refreshToken);
    }

    // Sử dụng findByToken + verifyExpiration của RefreshTokenService
    @Override
    @Transactional
    public AuthResponse refreshToken(RefreshTokenRequest request) {
        UserRefreshToken verifiedToken = refreshTokenService.findByToken(request.refreshToken())
                .map(refreshTokenService::verifyExpiration)
                .orElseThrow(() -> new BadRequestException("Refresh token không hợp lệ hoặc không tồn tại"));

        User user = verifiedToken.getUser();
        String newAccessToken = jwtService.generateToken(user);

        return buildAuthResponse(user, newAccessToken, verifiedToken.getTokenHash());
    }

    // logout: Sử dụng revokeAllUserToken của RefreshTokenService
    @Override
    @Transactional
    public void logout(String username) {
        User user = authUserRepository.findByUsername(username)
                .orElseThrow(() -> new NotFoundException("Không tìm thấy người dùng"));

        refreshTokenService.revokeAllUserToken(user.getId());
    }

    // Lấy thông tin user đang đăng nhập
    @Override
    @Transactional(readOnly = true)
    public CurrentUserResponse me(String username) {
        User user = authUserRepository.findByUsername(username)
                .orElseThrow(() -> new NotFoundException("Không tìm thấy người dùng"));

        return toCurrentUserResponse(user);
    }

    private CurrentUserResponse toCurrentUserResponse(User user) {
        return new CurrentUserResponse(
                user.getId(),
                user.getUsername(),
                user.getPhoneNumber(),
                user.getFullName(),
                user.getEmail(),
                user.getRole().name(),
                user.getCreatedAt());
    }

    private AuthResponse buildAuthResponse(User user, String accessToken, String refreshToken) {
        Instant expiresAt = Instant.now().plusMillis(jwtProperties.expiration());
        return new AuthResponse(
                accessToken,
                refreshToken,
                "Bearer",
                expiresAt,
                toCurrentUserResponse(user));
    }
}
