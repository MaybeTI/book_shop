package com.book.shop.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
@Schema(description = "DTO for adding a book to the shopping cart")
public class CartItemDto {
    @NotNull
    @Schema(description = "ID of the book to be added", example = "1")
    private Long bookId;

    @NotNull
    @Schema(description = "Quantity of the book", example = "2")
    private int quantity;
}

