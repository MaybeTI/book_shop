package com.book.shop.mapper;

import com.book.shop.config.MapperConfig;
import com.book.shop.dto.CreateBookRequestDto;
import com.book.shop.models.Book;
import org.mapstruct.Mapper;

@Mapper(config = MapperConfig.class)
public interface CreateBookMapper {
    Book toDto(Book book);

    Book toModel(CreateBookRequestDto bookDto);
}
