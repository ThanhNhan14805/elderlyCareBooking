package com.yo.elderlycarebooking.dto.auth;

import java.time.Instant;

public record AuthResponse(
        String accessToken,
        String refreshToken,
        String tokenType,
        Instant expiresAt,
        CurrentUserResponse user
) {
}
