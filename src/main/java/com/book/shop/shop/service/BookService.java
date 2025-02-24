package com.book.shop.service;

import com.book.shop.dto.BookDto;
import com.book.shop.dto.CreateOrUpdadeBookRequestDto;
import java.util.List;

public interface BookService {
    BookDto save(CreateOrUpdadeBookRequestDto bookRequestDto);

    List<BookDto> findAll();

    BookDto findById(Long id);

    void updateById(Long id, CreateOrUpdadeBookRequestDto bookRequestDto);

    void deleteById(Long id);
}