package com.book.shop.dto;

import java.math.BigDecimal;
import lombok.Data;

@Data
public class CreateOrUpdadeBookRequestDto {
    private String title;
    private String author;
    private String isbn;
    private BigDecimal price;
    private String description;
    private String coverImage;
}
