package com.yo.elderlycarebooking.repository.auth;

import com.yo.elderlycarebooking.domain.entity.User;
import com.yo.elderlycarebooking.domain.entity.UserRefreshToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RefreshTokenRepository extends JpaRepository<UserRefreshToken, Long> {

    // Tìm refresh token theo token string
    Optional<UserRefreshToken> findByTokenHash(String tokenHash);

    // Xoá toàn bộ refresh token theo userId
    void deleteAllByUserId(Long userId);

    // Thu hồi toàn bộ token cũ của user khi login/register mới
    @Modifying
    @Query("UPDATE UserRefreshToken r SET r.revoked = true WHERE r.user = :user AND r.revoked = false")
    void revokeAllActiveByUser(@Param("user") User user);
}
