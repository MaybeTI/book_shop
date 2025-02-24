package com.book.shop.service;

import com.book.shop.dto.BookDto;
import com.book.shop.dto.CreateOrUpdadeBookRequestDto;
import com.book.shop.mapper.BookMapper;
import com.book.shop.repository.BookRepository;
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
        return bookMapper.toDtoList(bookRepository.findAll());
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
