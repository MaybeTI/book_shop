package com.book.shop.shop.service;

import com.book.shop.shop.dto.BookDto;
import com.book.shop.shop.dto.BookSearchParametersDto;
import com.book.shop.shop.dto.CreateOrUpdateBookRequestDto;
import com.book.shop.shop.mapper.BookMapper;
import com.book.shop.shop.models.Book;
import com.book.shop.shop.repository.BookRepository;
import com.book.shop.shop.repository.BookSpecificationBuilder;
import jakarta.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class BookServiceImpl implements BookService {
    private final BookRepository bookRepository;
    private final BookMapper bookMapper;
    private final BookSpecificationBuilder bookSpecificationBuilder;

    @Override
    public BookDto save(CreateOrUpdateBookRequestDto bookRequestDto) {
        return bookMapper.toDto(bookRepository.save(bookMapper.toModel(bookRequestDto)));
    }

    @Override
    public List<BookDto> findAll() {
        return bookRepository.findAll().stream()
                .map(bookMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public BookDto findById(Long id) {
        return bookMapper.toDto(bookRepository.findById(id).get());
    }

    @Override
    public void deleteById(Long id) {
        bookRepository.deleteById(id);
    }

    @Override
    @Transactional
    public void updateById(Long id, CreateOrUpdateBookRequestDto bookRequestDto) {
        bookRepository.updateById(id, bookMapper.toModel(bookRequestDto));
    }

    @Override
    public List<BookDto> search(BookSearchParametersDto searchParameters) {
        Specification<Book> specification = bookSpecificationBuilder.build(searchParameters);
        return bookRepository.findAll(specification)
                .stream()
                .map(bookMapper::toDto)
                .toList();
    }
}
