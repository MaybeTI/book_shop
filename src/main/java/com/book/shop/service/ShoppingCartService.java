package com.book.shop.service;

import com.book.shop.dto.CartItemDto;
import com.book.shop.dto.ShoppingCartDto;

public interface ShoppingCartService {
    ShoppingCartDto save(ShoppingCartDto shoppingCartDto);

    ShoppingCartDto findByUserId(Long userId);

    void deleteCartItem(Long userId, Long cartItemId);

    ShoppingCartDto updateCartItemQuantity(Long userId, Long cartItemId, int newQuantity);

    ShoppingCartDto addBookToCart(Long userId, CartItemDto cartItemDto);
}
