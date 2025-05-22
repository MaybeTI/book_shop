package com.book.shop.service;

import com.book.shop.dto.OrderItemDto;
import java.util.List;
import org.springframework.data.domain.Pageable;

public interface OrderItemService {
    List<OrderItemDto> getOrderItemsByOrderId(Long orderId, Pageable pageable);

    OrderItemDto getOrderItemByIdAndOrderId(Long itemId, Long orderId);
}
