package com.book.shop.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import java.util.Set;
import lombok.Data;

@Data
@Schema(description = "DTO representing the shopping cart")
public class ShoppingCartDto {
    @Schema(description = "Shopping cart ID", example = "5")
    private Long id;

    @NotNull
    @Schema(description = "ID of the user who owns the cart", example = "42")
    private Long userId;

    @Schema(description = "Set of items in the cart")
    private Set<CartItemResponseDto> cartItems;
}

