package com.book.shop.mapper;

import com.book.shop.config.MapperConfig;
import com.book.shop.dto.CartItemDto;
import com.book.shop.dto.CartItemResponseDto;
import com.book.shop.models.CartItem;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(config = MapperConfig.class, uses = {BookMapper.class})
public interface CartItemMapper {
    CartItemDto toDto(CartItem cartItem);

    CartItem toModel(CartItemDto cartItemDto);

    @Mapping(source = "book.id", target = "bookId")
    @Mapping(source = "book.title", target = "bookTitle")
    CartItemResponseDto toResponseDto(CartItem cartItem);
}
