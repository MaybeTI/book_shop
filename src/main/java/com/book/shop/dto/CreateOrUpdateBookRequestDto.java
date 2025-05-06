package com.book.shop.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import java.math.BigDecimal;
import java.util.List;
import lombok.Data;

@Data
@Schema(description = "DTO for creating or updating a book")
public class CreateOrUpdateBookRequestDto {

    @NotNull
    @Schema(description = "Title of the book", example = "The Pragmatic Programmer")
    private String title;

    @NotNull
    @Schema(description = "Author of the book", example = "Andy Hunt and Dave Thomas")
    private String author;

    @NotNull
    @Schema(description = "ISBN number of the book", example = "978-0135957059")
    private String isbn;

    @NotNull
    @Positive
    @Schema(description = "Price of the book", example = "39.99")
    private BigDecimal price;

    @NotNull
    @Schema(description = "Description of the book",
            example = "A guide to becoming a better software developer")
    private String description;

    @NotNull
    @Schema(description = "URL of the book cover image",
            example = "https://example.com/pragmatic.jpg")
    private String coverImage;

    @Schema(description = "List of category IDs associated with the book",
            example = "[1, 2, 3]")
    private List<Long> categoryIds;
}
