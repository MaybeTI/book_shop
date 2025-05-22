package com.book.shop.controller;

import com.book.shop.dto.CreateOrderDto;
import com.book.shop.dto.OrderDto;
import com.book.shop.dto.UpdateOrderDto;
import com.book.shop.models.User;
import com.book.shop.service.OrderService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/orders")
@Tag(name = "Orders", description = "Order management API")
public class OrderController {
    private final OrderService orderService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Place an order",
            description = "Place an order for books in the shopping cart")
    @PreAuthorize("hasRole('USER')")
    public OrderDto createOrder(@AuthenticationPrincipal User user,
                                @RequestBody @Valid CreateOrderDto createOrderDto) {
        return orderService.createOrder(user.getId(), createOrderDto);
    }

    @GetMapping
    @Operation(summary = "Retrieve user's order history",
            description = "Get a list of all orders placed by the authenticated user")
    @PreAuthorize("hasRole('USER')")
    public List<OrderDto> getOrders(@AuthenticationPrincipal User user,
                                    Pageable pageable) {
        return orderService.getOrders(user.getId(), pageable);
    }

    @PatchMapping("/{id}")
    @Operation(summary = "Update order status",
            description = "Allows an Admin to update the status of an order")
    @PreAuthorize("hasRole('ADMIN')")
    public void changeStatus(@PathVariable("id") Long id,
                             @RequestBody @Valid UpdateOrderDto updateOrderDto) {
        orderService.updateOrder(id, updateOrderDto);
    }
}
