package com.book.shop.service;

import com.book.shop.dto.CreateOrderDto;
import com.book.shop.dto.OrderDto;
import com.book.shop.dto.UpdateOrderDto;
import java.util.List;
import org.springframework.data.domain.Pageable;

public interface OrderService {
    OrderDto createOrder(Long userId, CreateOrderDto orderDto);

    List<OrderDto> getOrders(Long userId, Pageable pageable);

    void updateOrder(Long orderId, UpdateOrderDto updateOrderDto);
}
