package com.yo.elderlycarebooking.dto.auth;


import java.sql.Timestamp;

public record CurrentUserResponse(
        Long      id,
        String    username,
        String    phoneNumber,
        String    fullName,
        String    email,
        String    role,
        Timestamp createdAt
) {
}
