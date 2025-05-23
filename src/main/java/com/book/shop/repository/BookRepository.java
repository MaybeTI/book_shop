package com.book.shop.repository;

import com.book.shop.models.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface BookRepository extends JpaRepository<Book, Long>, JpaSpecificationExecutor<Book> {
    @Query("UPDATE Book b set b.title = :#{#book.title},"
            + "b.author = :#{#book.author},"
            + "b.isbn = :#{#book.isbn},"
            + "b.price = :#{#book.price},"
            + "b.description = :#{#book.description},"
            + "b.coverImage = :#{#book.coverImage} "
            + "WHERE b.id = :id")
    @Modifying
    void updateById(@Param("id") Long id, @Param("book") Book book);
}
