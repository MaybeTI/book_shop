package com.book.shop.shop;

import com.book.shop.shop.models.Book;
import com.book.shop.shop.service.BookService;
import java.math.BigDecimal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class BookShopApplication {
    @Autowired
    private BookService bookService;

    public static void main(String[] args) {
        SpringApplication.run(BookShopApplication.class, args);
    }

    @Bean
    public CommandLineRunner commandLineRunner() {
        return args -> {
            Book book = new Book();
            book.setTitle("Book Title");
            book.setAuthor("Book Author");
            book.setIsbn("978-3-16-148410-0");
            book.setPrice(BigDecimal.valueOf(15.45));
            bookService.save(book);

            System.out.println(bookService.findAll());
        };
    }
}
