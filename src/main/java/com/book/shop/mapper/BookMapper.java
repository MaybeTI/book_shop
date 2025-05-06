package com.book.shop.mapper;

import com.book.shop.config.MapperConfig;
import com.book.shop.dto.BookDto;
import com.book.shop.dto.BookDtoWithoutCategoryIds;
import com.book.shop.dto.CreateOrUpdateBookRequestDto;
import com.book.shop.models.Book;
import com.book.shop.models.Category;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import org.mapstruct.AfterMapping;
import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(config = MapperConfig.class)
public interface BookMapper {
    BookDto toDto(Book book);

    Book toModel(CreateOrUpdateBookRequestDto bookDto);

    @IterableMapping(elementTargetType = BookDto.class)
    List<BookDto> toDtoList(List<Book> books);

    BookDtoWithoutCategoryIds toDtoWithoutCategories(Book book);

    @AfterMapping
    default void setCategoryIds(@MappingTarget BookDto bookDto, Book book) {
        List<Long> categoryIds = book.getCategories().stream()
                .map(Category::getId)
                .collect(Collectors.toList());
        bookDto.setCategoryIds(categoryIds);
    }

    @AfterMapping
    default void mapCategories(
            @MappingTarget Book book,
            CreateOrUpdateBookRequestDto bookDto) {
        if (bookDto.getCategoryIds() != null) {
            Set<Category> categories = bookDto.getCategoryIds().stream()
                    .map(id -> {
                        Category category = new Category();
                        category.setId(id);
                        return category;
                    })
                    .collect(Collectors.toSet());
            book.setCategories(categories);
        }
    }
}
