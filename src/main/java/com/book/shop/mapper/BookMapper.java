package com.book.shop.mapper;

import com.book.shop.config.MapperConfig;
import com.book.shop.dto.BookDto;
import com.book.shop.dto.CreateOrUpdadeBookRequestDto;
import com.book.shop.models.Book;
import java.util.List;
import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;

@Mapper(config = MapperConfig.class)
public interface BookMapper {
    BookDto toDto(Book book);

    Book toModel(CreateOrUpdadeBookRequestDto bookDto);

    @IterableMapping(elementTargetType = BookDto.class)
    List<BookDto> toDtoList(List<Book> books);
}
