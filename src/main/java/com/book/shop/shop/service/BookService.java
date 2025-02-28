package com.book.shop.shop.service;

import com.book.shop.shop.dto.BookDto;
import com.book.shop.shop.dto.BookSearchParametersDto;
import com.book.shop.shop.dto.CreateOrUpdateBookRequestDto;
import java.util.List;

public interface BookService {
    BookDto save(CreateOrUpdateBookRequestDto bookRequestDto);

    List<BookDto> findAll();

    BookDto findById(Long id);

    void updateById(Long id, CreateOrUpdateBookRequestDto bookRequestDto);

    void deleteById(Long id);

    List<BookDto> search(BookSearchParametersDto searchParameters);
}
