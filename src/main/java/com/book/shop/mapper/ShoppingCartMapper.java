package com.book.shop.mapper;

import com.book.shop.config.MapperConfig;
import com.book.shop.dto.CartItemResponseDto;
import com.book.shop.dto.ShoppingCartDto;
import com.book.shop.models.CartItem;
import com.book.shop.models.ShoppingCart;
import java.util.Set;
import java.util.stream.Collectors;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

@Mapper(config = MapperConfig.class, uses = {CartItemMapper.class})
public interface ShoppingCartMapper {
    @Mapping(source = "user.id", target = "userId")
    @Mapping(source = "cartItems", target = "cartItems",
            qualifiedByName = "mapCartItemsToResponseDto")
    ShoppingCartDto toDto(ShoppingCart shoppingCart);

    ShoppingCart toModel(ShoppingCartDto shoppingCartDto);

    @Named("mapCartItemsToResponseDto")
    default Set<CartItemResponseDto> mapCartItemsToResponseDto(Set<CartItem> cartItems) {
        return cartItems.stream()
                .map(cartItem -> {
                    CartItemResponseDto dto = new CartItemResponseDto();
                    dto.setId(cartItem.getId());
                    dto.setBookId(cartItem.getBook().getId());
                    dto.setBookTitle(cartItem.getBook().getTitle());
                    dto.setQuantity(cartItem.getQuantity());
                    return dto;
                })
                .collect(Collectors.toSet());
    }
}
