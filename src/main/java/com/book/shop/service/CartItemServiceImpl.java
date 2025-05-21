package com.book.shop.service;

import com.book.shop.dto.CartItemDto;
import com.book.shop.mapper.CartItemMapper;
import com.book.shop.repository.CartItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CartItemServiceImpl implements CartItemService {
    private final CartItemMapper cartItemMapper;
    private final CartItemRepository cartItemRepository;

    @Override
    public CartItemDto save(CartItemDto cartItemRequestDto) {
        return cartItemMapper.toDto(cartItemRepository
                .save(cartItemMapper.toModel(cartItemRequestDto)));
    }
}
