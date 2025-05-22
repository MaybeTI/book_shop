package com.book.shop.mapper;

import com.book.shop.config.MapperConfig;
import com.book.shop.dto.OrderItemDto;
import com.book.shop.models.OrderItem;
import org.mapstruct.Mapper;

@Mapper(config = MapperConfig.class)
public interface OrderItemMapper {
    OrderItemDto toDto(OrderItem orderItem);

    OrderItem toModel(OrderItemDto orderItemDto);
}
