package com.book.shop.shop.service;

import com.book.shop.shop.dto.BookDto;
import com.book.shop.shop.dto.CreateOrUpdadeBookRequestDto;
import java.util.List;

public interface BookService {
    BookDto save(CreateOrUpdadeBookRequestDto bookRequestDto);

    List<BookDto> findAll();

    BookDto findById(Long id);

    void updateById(Long id, CreateOrUpdadeBookRequestDto bookRequestDto);

    void deleteById(Long id);
}
