package com.book.shop.shop.mapper;

import com.book.shop.shop.config.MapperConfig;
import com.book.shop.shop.dto.CreateOrUpdateBookRequestDto;
import com.book.shop.shop.models.Book;
import org.mapstruct.Mapper;

@Mapper(config = MapperConfig.class)
public interface CreateBookMapper {
    Book toDto(Book book);

    Book toModel(CreateOrUpdateBookRequestDto bookDto);
}
