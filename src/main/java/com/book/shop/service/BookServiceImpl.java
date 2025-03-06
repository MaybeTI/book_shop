package com.book.shop.service;

import com.book.shop.dto.BookDto;
import com.book.shop.dto.BookSearchParametersDto;
import com.book.shop.dto.CreateOrUpdateBookRequestDto;
import com.book.shop.mapper.BookMapper;
import com.book.shop.repository.BookRepository;
import com.book.shop.repository.BookSpecificationBuilder;
import jakarta.transaction.Transactional;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
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
    public List<BookDto> findAll(Pageable pageable) {
        return bookMapper.toDtoList(bookRepository.findAll(pageable).getContent());
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
    public List<BookDto> search(BookSearchParametersDto searchParameters, Pageable pageable) {
        return bookMapper.toDtoList(bookRepository
                .findAll(bookSpecificationBuilder.build(searchParameters), pageable).getContent());
    }
}
