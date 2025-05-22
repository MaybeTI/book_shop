package com.book.shop.mapper;

import com.book.shop.config.MapperConfig;
import com.book.shop.dto.CreateOrderDto;
import com.book.shop.dto.OrderDto;
import com.book.shop.models.Order;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(config = MapperConfig.class, uses = OrderItemMapper.class)
public interface OrderMapper {
    @Mapping(source = "user.id", target = "userId")
    OrderDto toDto(Order order);

    Order toModel(CreateOrderDto createOrderDto);
}
