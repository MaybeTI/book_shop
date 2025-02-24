package com.book.shop.shop.service;

import com.book.shop.shop.models.Book;
import java.util.List;

public interface BookService {
    Book save(Book book);

    List<Book> findAll();
}
