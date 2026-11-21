package com.yo.elderlycarebooking.security.service;

import com.yo.elderlycarebooking.common.exception.BadRequestException;
import com.yo.elderlycarebooking.common.exception.NotFoundException;
import com.yo.elderlycarebooking.config.AppJwtProperties;
import com.yo.elderlycarebooking.domain.entity.User;
import com.yo.elderlycarebooking.domain.entity.UserRefreshToken;
import com.yo.elderlycarebooking.repository.auth.AuthUserRepository;
import com.yo.elderlycarebooking.repository.auth.RefreshTokenRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class RefreshTokenService {

    private final AppJwtProperties appJwtProperties;
    private final RefreshTokenRepository refreshTokenRepository;
    private final AuthUserRepository authUserRepository;

    @Transactional
    public UserRefreshToken createRefreshToken(Long userId) {
        User user = authUserRepository.findById(userId)
                .orElseThrow(() -> new NotFoundException("User not exist!"));

        // Vô hiệu hoá các refresh token cũ đang active của user
        refreshTokenRepository.revokeAllActiveByUser(user);
        // Instant: dùng instant để biểu diễn thời điểm hiện tại
        Instant now = Instant.now();
        UserRefreshToken refreshToken = UserRefreshToken.builder()
                .user(user)
                .tokenHash(UUID.randomUUID().toString())
                .expiresAt(Timestamp.from(now.plusMillis(appJwtProperties.refreshExpiration())))
                .createdAt(Timestamp.from(now))
                .revoked(false)
                .build();

        return refreshTokenRepository.save(refreshToken);
    }

    @Transactional(readOnly = true)
    public Optional<UserRefreshToken> findByToken(String token) {
        return refreshTokenRepository.findByTokenHash(token);
    }

    @Transactional
    public UserRefreshToken verifyExpiration(UserRefreshToken refreshToken) {
        if (refreshToken.isRevoked()) {
            throw new BadRequestException("Refresh token has been revoked!");
        }
        if (refreshToken.getExpiresAt().toInstant().isBefore(Instant.now())) {
            refreshToken.setRevoked(true);
            refreshTokenRepository.save(refreshToken);
            throw new BadRequestException("Refresh token expired!");
        }
        return refreshToken;
    }

    @Transactional
    public void revokeAllUserToken(Long userId) {
        User user = authUserRepository.findById(userId)
                .orElseThrow(() -> new NotFoundException("User not exist!"));
        refreshTokenRepository.revokeAllActiveByUser(user);
    }
}
