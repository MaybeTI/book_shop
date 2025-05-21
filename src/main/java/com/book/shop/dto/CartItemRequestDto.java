package com.book.shop.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
@Schema(description = "DTO for updating quantity of a cart item")
public class CartItemRequestDto {
    @NotNull
    @Min(1)
    @Schema(description = "New quantity for the cart item", example = "3", minimum = "1")
    private int quantity;
}

