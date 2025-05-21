package com.book.shop.controller;

import com.book.shop.dto.BookDto;
import com.book.shop.dto.BookSearchParametersDto;
import com.book.shop.dto.CreateOrUpdateBookRequestDto;
import com.book.shop.service.BookService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Book Shop", description = "Operations related to book management")
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/books")
public class BookController {
    private static final String SEARCH_PATH = "/search";
    private static final String ID_PATH = "/{id}";
    private static final String ROLE_ADMIN = "hasRole('ADMIN')";
    private static final String ROLE_ADMIN_USER = "hasAnyRole('ADMIN', 'USER')";
    private final BookService bookService;

    @GetMapping
    @Operation(summary = "Get all books", description = "Retrieve a paginated list of all books.")
    @ApiResponse(responseCode = "200", description = "List of books successfully retrieved.")
    @PreAuthorize(ROLE_ADMIN_USER)
    public List<BookDto> getAll(Pageable pageable) {
        return bookService.findAll(pageable);
    }

    @GetMapping(ID_PATH)
    @Operation(summary = "Get book by ID", description = "Retrieve book details by its unique ID.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Book found successfully."),
            @ApiResponse(responseCode = "404", description = "Book not found.")
    })
    @PreAuthorize(ROLE_ADMIN_USER)
    public BookDto getBookById(
            @Parameter(description = "ID of the book to retrieve", required = true)
            @PathVariable Long id) {
        return bookService.findById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Create a new book", description = "Add a new book to the store.")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Book successfully created."),
            @ApiResponse(responseCode = "400", description = "Invalid book data provided.")
    })
    @PreAuthorize(ROLE_ADMIN)
    public BookDto createBook(
            @RequestBody @Valid CreateOrUpdateBookRequestDto bookRequestDto) {
        return bookService.save(bookRequestDto);
    }

    @PutMapping(ID_PATH)
    @Operation(summary = "Update a book", description = "Update the details of an existing book.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Book updated successfully."),
            @ApiResponse(responseCode = "404", description = "Book not found.")
    })
    @PreAuthorize(ROLE_ADMIN)
    public void updateBook(
            @Parameter(description = "ID of the book to update", required = true)
            @PathVariable Long id,
            @RequestBody @Valid CreateOrUpdateBookRequestDto bookRequestDto) {
        bookService.updateById(id, bookRequestDto);
    }

    @DeleteMapping(ID_PATH)
    @Operation(summary = "Delete a book", description = "Remove a book from the store by ID.")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Book successfully deleted."),
            @ApiResponse(responseCode = "404", description = "Book not found.")
    })
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PreAuthorize(ROLE_ADMIN)
    public void deleteBook(
            @Parameter(description = "ID of the book to delete", required = true)
            @PathVariable Long id) {
        bookService.deleteById(id);
    }

    @GetMapping(SEARCH_PATH)
    @Operation(
            summary = "Search for books", description = "Search for books using various parameters."
    )
    @ApiResponse(responseCode = "200", description = "Search results returned successfully.")
    public List<BookDto> searchBooks(
            @Parameter(description = "Search parameters for filtering books")
            BookSearchParametersDto searchParameters,
            Pageable pageable) {
        return bookService.search(searchParameters, pageable);
    }
}
