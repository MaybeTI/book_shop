package com.book.shop.shop.service;

import com.book.shop.shop.dto.BookDto;
import com.book.shop.shop.dto.CreateBookRequestDto;
import java.util.List;

public interface BookService {
    BookDto save(CreateBookRequestDto bookRequestDto);

    List<BookDto> findAll();

    BookDto findById(Long id);
}
