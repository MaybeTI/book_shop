package com.book.shop.shop.mapper;

import com.book.shop.shop.config.MapperConfig;
import com.book.shop.shop.dto.BookDto;
import com.book.shop.shop.dto.CreateOrUpdadeBookRequestDto;
import com.book.shop.shop.models.Book;
import org.mapstruct.Mapper;

@Mapper(config = MapperConfig.class)
public interface BookMapper {
    BookDto toDto(Book book);

    Book toModel(CreateOrUpdadeBookRequestDto bookDto);
}
