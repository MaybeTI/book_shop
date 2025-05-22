package com.book.shop.dto;

import io.swagger.v3.oas.annotations.media.Schema;

public record UserResponseDto(
        @Schema(description = "Unique identifier of the user", example = "1")
        Long id,

        @Schema(description = "Email address of the user", example = "user@example.com")
        String email,

        @Schema(description = "First name of the user", example = "John")
        String firstName,

        @Schema(description = "Last name of the user", example = "Doe")
        String lastName,

        @Schema(description = "Shipping address of the user",
                example = "123 Main St, City, Country")
        String shippingAddress
) {
}
