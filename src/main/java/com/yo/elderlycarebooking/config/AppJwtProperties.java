package com.yo.elderlycarebooking.config;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.validation.annotation.Validated;


@Validated
@ConfigurationProperties(prefix = "jwt")
public record AppJwtProperties(

        @NotNull
        Long expiration,          // maps to jwt.expiration

        @NotBlank
        String secret,            // maps to jwt.secret

        @NotNull
        Long refreshExpiration    // maps to jwt.refresh-expiration (Spring relaxed binding)

) {}
