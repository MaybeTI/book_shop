package com.book.shop.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import java.math.BigDecimal;
import lombok.Data;

@Data
@Schema(description = "DTO representing a book")
public class BookDtoWithoutCategoryIds {
    @Schema(description = "Unique identifier of the book", example = "1")
    private Long id;

    @Schema(description = "Title of the book", example = "Effective Java")
    private String title;

    @Schema(description = "Author of the book", example = "Joshua Bloch")
    private String author;

    @Schema(description = "ISBN number of the book", example = "978-0134685991")
    private String isbn;

    @Schema(description = "Price of the book", example = "45.99")
    private BigDecimal price;

    @Schema(description = "Description of the book",
            example = "A guide to best practices in Java programming")
    private String description;

    @Schema(description = "URL of the book cover image", example = "https://example.com/cover.jpg")
    private String coverImage;
}
