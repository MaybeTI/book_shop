package com.book.shop.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;

public record UserLoginRequestDto(
        @NotEmpty
        @Email
        @Schema(description = "User email for login",
                example = "admin@example.com", required = true)
        String email,

        @NotEmpty
        @Schema(description = "User password for login",
                example = "strongPassword123", required = true)
        String password
) {
}
