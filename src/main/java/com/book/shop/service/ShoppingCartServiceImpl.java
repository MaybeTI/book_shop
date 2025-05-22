package com.book.shop.service;

import com.book.shop.dto.CartItemDto;
import com.book.shop.dto.ShoppingCartDto;
import com.book.shop.exceptions.EntityNotFoundException;
import com.book.shop.mapper.CartItemMapper;
import com.book.shop.mapper.ShoppingCartMapper;
import com.book.shop.models.Book;
import com.book.shop.models.CartItem;
import com.book.shop.models.ShoppingCart;
import com.book.shop.repository.BookRepository;
import com.book.shop.repository.ShoppingCartRepository;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ShoppingCartServiceImpl implements ShoppingCartService {
    private final ShoppingCartRepository shoppingCartRepository;
    private final ShoppingCartMapper shoppingCartMapper;
    private final BookRepository bookRepository;
    private final CartItemService cartItemService;
    private final CartItemMapper cartItemMapper;

    @Override
    public ShoppingCartDto save(ShoppingCartDto shoppingCartDto) {
        return shoppingCartMapper.toDto(shoppingCartRepository
                .save(shoppingCartMapper.toModel(shoppingCartDto)));
    }

    @Override
    public ShoppingCartDto findByUserId(Long userId) {
        ShoppingCart shoppingCart = shoppingCartRepository.findByUserId(userId)
                .orElseThrow(() -> new EntityNotFoundException(
                        "Shopping cart not found for user id: " + userId));
        return shoppingCartMapper.toDto(shoppingCart);
    }

    @Override
    public ShoppingCartDto updateCartItemQuantity(Long userId, Long cartItemId, int newQuantity) {
        ShoppingCart shoppingCart = shoppingCartRepository.findByUserId(userId)
                .orElseThrow(() -> new RuntimeException("Shopping cart not found"));

        CartItem cartItem = shoppingCart.getCartItems().stream()
                .filter(item -> item.getId().equals(cartItemId))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Cart item not found"));
        cartItem.setQuantity(newQuantity);

        return shoppingCartMapper.toDto(shoppingCartRepository.save(shoppingCart));
    }

    @Override
    public ShoppingCartDto addBookToCart(Long userId, CartItemDto cartItemDto) {
        ShoppingCart shoppingCart = shoppingCartRepository.findByUserId(userId)
                .orElseThrow(() -> new EntityNotFoundException(
                        "Shopping cart not found for user id: " + userId));

        Book book = bookRepository.findById(cartItemDto.getBookId())
                .orElseThrow(() -> new EntityNotFoundException(
                        "Book not found with id: " + cartItemDto.getBookId()));

        Optional<CartItem> existingCartItem = shoppingCart.getCartItems().stream()
                .filter(item -> item.getBook().getId().equals(book.getId()))
                .findFirst();

        if (existingCartItem.isPresent()) {
            CartItem cartItem = existingCartItem.get();
            cartItem.setQuantity(cartItem.getQuantity() + cartItemDto.getQuantity());
        } else {
            CartItem newCartItem = new CartItem();
            newCartItem.setShoppingCart(shoppingCart);
            newCartItem.setBook(book);
            newCartItem.setQuantity(cartItemDto.getQuantity());
            shoppingCart.getCartItems().add(newCartItem);
        }
        return shoppingCartMapper.toDto(shoppingCartRepository.save(shoppingCart));
    }

    @Override
    public void deleteCartItem(Long userId, Long cartItemId) {
        ShoppingCart shoppingCart = shoppingCartRepository.findByUserId(userId)
                .orElseThrow(() -> new EntityNotFoundException(
                        "Shopping cart not found for user id: " + userId));

        boolean removed = shoppingCart.getCartItems()
                .removeIf(item -> item.getId().equals(cartItemId));

        if (!removed) {
            throw new EntityNotFoundException(
                    "Cart item not found with id: " + cartItemId + " in user's cart.");
        }
        shoppingCartRepository.save(shoppingCart);
    }
}
