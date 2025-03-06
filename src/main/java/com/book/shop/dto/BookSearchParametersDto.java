package com.book.shop.dto;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "DTO for searching books by title or author")
public record BookSearchParametersDto(
        @Schema(description = "Array of book titles to search for",
                example = "[\"Effective Java\", \"Clean Code\"]")
        String[] titles,

        @Schema(description = "Array of book authors to search for",
                example = "[\"Joshua Bloch\", \"Robert C. Martin\"]")
        String[] authors
) {}
