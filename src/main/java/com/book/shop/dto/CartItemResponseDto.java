package com.book.shop.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
@Schema(description = "DTO representing a cart item in the response")
public class CartItemResponseDto {
    @NotNull
    @Schema(description = "ID of the cart item", example = "10")
    private Long id;

    @NotNull
    @Schema(description = "ID of the book", example = "1")
    private Long bookId;

    @NotNull
    @Schema(description = "Title of the book", example = "Clean Code")
    private String bookTitle;

    @NotNull
    @Schema(description = "Quantity of the book in the cart", example = "2")
    private int quantity;
}

