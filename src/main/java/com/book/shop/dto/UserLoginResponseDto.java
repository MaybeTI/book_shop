package com.book.shop.dto;

import io.swagger.v3.oas.annotations.media.Schema;

public record UserLoginResponseDto(
        @Schema(description = "JWT access token returned after successful login",
                example = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9...")
        String token
) {
}
