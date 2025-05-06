package com.book.shop.mapper;

import com.book.shop.config.MapperConfig;
import com.book.shop.dto.CategoryDto;
import com.book.shop.models.Category;
import org.mapstruct.Mapper;

@Mapper(config = MapperConfig.class)
public interface CategoryMapper {
    CategoryDto toDto(Category category);

    Category toModel(CategoryDto categoryDto);
}
