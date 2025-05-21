package com.book.shop.service;

import com.book.shop.dto.CartItemDto;

public interface CartItemService {
    CartItemDto save(CartItemDto cartItemRequestDto);
}
