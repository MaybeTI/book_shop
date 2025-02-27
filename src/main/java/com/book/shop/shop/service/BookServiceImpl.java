package com.book.shop.shop.service;

import com.book.shop.shop.dto.BookDto;
import com.book.shop.shop.dto.CreateOrUpdadeBookRequestDto;
import com.book.shop.shop.mapper.BookMapper;
import com.book.shop.shop.repository.BookRepository;
import jakarta.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class BookServiceImpl implements BookService {
    private final BookRepository bookRepository;
    private final BookMapper bookMapper;

    @Override
    public BookDto save(CreateOrUpdadeBookRequestDto bookRequestDto) {
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
    public void updateById(Long id, CreateOrUpdadeBookRequestDto bookRequestDto) {
        bookRepository.updateById(id, bookMapper.toModel(bookRequestDto));
    }
}
