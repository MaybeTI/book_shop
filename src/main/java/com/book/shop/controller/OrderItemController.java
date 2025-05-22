package com.book.shop.controller;

import com.book.shop.dto.OrderItemDto;
import com.book.shop.service.OrderItemServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/orders/{orderId}/items")
@Tag(name = "Order Items", description = "Order item management API")
public class OrderItemController {
    private final OrderItemServiceImpl orderItemService;

    @GetMapping
    @Operation(summary = "Retrieve all OrderItems for a specific order",
            description = "Get a list of all order items within a given order")
    @PreAuthorize("hasRole('USER')")
    public List<OrderItemDto> getOrderItemsForOrder(@PathVariable Long orderId,
                                                    Pageable pageable) {
        return orderItemService.getOrderItemsByOrderId(orderId, pageable);
    }

    @GetMapping("/{itemId}")
    @Operation(summary = "Retrieve a specific OrderItem within an order",
            description = "Get details of a specific order item belonging to a particular order")
    @PreAuthorize("hasRole('USER')")
    public OrderItemDto getOrderItemById(@PathVariable Long orderId, @PathVariable Long itemId) {
        return orderItemService.getOrderItemByIdAndOrderId(itemId, orderId);
    }
}
