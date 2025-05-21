package com.book.shop.controller;

import com.book.shop.dto.CartItemDto;
import com.book.shop.dto.CartItemRequestDto;
import com.book.shop.dto.ShoppingCartDto;
import com.book.shop.models.User;
import com.book.shop.service.ShoppingCartService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Shopping cart management", description = "Endpoints for managing shopping carts")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/cart")
public class ShoppingCartController {
    private final ShoppingCartService shoppingCartService;

    @Operation(summary = "Get shopping cart for authenticated user")
    @ApiResponse(responseCode = "200", description = "Shopping cart returned",
            content = @Content(schema = @Schema(implementation = ShoppingCartDto.class)))
    @PreAuthorize("hasRole('USER')")
    @GetMapping
    public ShoppingCartDto getShoppingCart(@AuthenticationPrincipal User user) {
        return shoppingCartService.findByUserId(user.getId());
    }

    @Operation(summary = "Add book to cart")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Book added to cart",
                    content = @Content(schema = @Schema(implementation = ShoppingCartDto.class))),
            @ApiResponse(responseCode = "400", description = "Invalid input")
    })
    @PreAuthorize("hasRole('USER')")
    @PostMapping
    public ShoppingCartDto addBookToCart(@AuthenticationPrincipal User user,
                                         @RequestBody @Valid CartItemDto cartItemDto) {
        return shoppingCartService.addBookToCart(user.getId(), cartItemDto);
    }

    @Operation(summary = "Update book quantity in cart")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Quantity updated",
                    content = @Content(schema = @Schema(implementation = ShoppingCartDto.class))),
            @ApiResponse(responseCode = "400", description = "Invalid input")
    })
    @PreAuthorize("hasRole('USER')")
    @PutMapping("/cart-items/{cartItemId}")
    public ShoppingCartDto updateBookQuantity(
            @AuthenticationPrincipal User user,
            @PathVariable Long cartItemId,
            @RequestBody @Valid CartItemRequestDto cartItemRequestDto
    ) {
        return shoppingCartService.updateCartItemQuantity(user.getId(),
                cartItemId, cartItemRequestDto.getQuantity());
    }

    @Operation(summary = "Delete item from cart")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Item deleted"),
            @ApiResponse(responseCode = "404", description = "Item not found")
    })
    @PreAuthorize("hasRole('USER')")
    @DeleteMapping("/items/{cartItemId}")
    public void deleteCartItem(@AuthenticationPrincipal User user, @PathVariable Long cartItemId) {
        shoppingCartService.deleteCartItem(user.getId(), cartItemId);
    }
}
