package com.book.shop.service;

import com.book.shop.dto.CreateOrderDto;
import com.book.shop.dto.OrderDto;
import com.book.shop.dto.UpdateOrderDto;
import com.book.shop.mapper.OrderMapper;
import com.book.shop.models.Book;
import com.book.shop.models.CartItem;
import com.book.shop.models.Order;
import com.book.shop.models.OrderItem;
import com.book.shop.models.ShoppingCart;
import com.book.shop.models.Status;
import com.book.shop.models.User;
import com.book.shop.repository.CartItemRepository;
import com.book.shop.repository.OrderItemRepository;
import com.book.shop.repository.OrderRepository;
import com.book.shop.repository.ShoppingCartRepository;
import com.book.shop.repository.UserRepository;
import jakarta.transaction.Transactional;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository;
    private final OrderMapper orderMapper;
    private final UserRepository userRepository;
    private final ShoppingCartRepository shoppingCartRepository;
    private final CartItemRepository cartItemRepository;
    private final OrderItemRepository orderItemRepository;

    @Override
    @Transactional
    public void updateOrder(Long orderId, UpdateOrderDto updateOrderDto) {
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new RuntimeException("Order not found with ID: " + orderId));

        order.setStatus(updateOrderDto.getStatus());
        orderRepository.save(order);
    }

    @Override
    public List<OrderDto> getOrders(Long userId, Pageable pageable) {
        return orderRepository.findAllByUserId(userId, pageable)
                .map(orderMapper::toDto)
                .toList();
    }

    @Override
    @Transactional
    public OrderDto createOrder(Long userId, CreateOrderDto createOrderDto) {
        User currentUser = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found!"));

        ShoppingCart shoppingCart = shoppingCartRepository.findByUserId(userId)
                .orElseThrow(() -> new RuntimeException(
                        "Shopping cart not found for user: " + userId));

        if (shoppingCart.getCartItems() == null || shoppingCart.getCartItems().isEmpty()) {
            throw new IllegalArgumentException(
                    "Cannot place an order with an empty shopping cart.");
        }

        Order order = orderMapper.toModel(createOrderDto);

        order.setUser(currentUser);
        order.setOrderDate(LocalDateTime.now());
        order.setStatus(Status.PENDING);

        BigDecimal totalOrderPrice = BigDecimal.ZERO;
        Set<OrderItem> orderItems = new HashSet<>();

        for (CartItem cartItem : shoppingCart.getCartItems()) {
            Book book = cartItem.getBook();
            int quantity = cartItem.getQuantity();

            OrderItem orderItem = new OrderItem();
            orderItem.setOrder(order);
            orderItem.setBook(book);
            orderItem.setQuantity(quantity);
            orderItem.setPrice(book.getPrice());

            orderItems.add(orderItem);

            totalOrderPrice = totalOrderPrice.add(book.getPrice()
                    .multiply(BigDecimal.valueOf(quantity)));
        }

        order.setOrderItems(orderItems);
        order.setTotal(totalOrderPrice);

        Order savedOrder = orderRepository.save(order);
        orderItems.forEach(item -> item.setOrder(savedOrder));
        orderItemRepository.saveAll(orderItems);

        cartItemRepository.deleteAll(shoppingCart.getCartItems());
        shoppingCart.getCartItems().clear();
        shoppingCartRepository.save(shoppingCart);

        return orderMapper.toDto(savedOrder);
    }
}
