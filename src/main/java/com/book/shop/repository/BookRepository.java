package com.book.shop.repository;

import com.book.shop.models.Book;
import java.util.List;

public interface BookRepository {
    Book save(Book book);

    List<Book> findAll();

    Book findById(Long id);
}
