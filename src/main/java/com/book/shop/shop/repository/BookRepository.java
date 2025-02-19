package com.book.shop.shop.repository;

import com.book.shop.shop.models.Book;
import java.util.List;

public interface BookRepository {
    Book save(Book book);

    List<Book> findAll();
}
