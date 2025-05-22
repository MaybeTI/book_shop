package com.book.shop.repository;

import com.book.shop.models.Order;
import java.util.Set;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {
    Set<Order> findAllByUserId(Long userId);

    Page<Order> findAllByUserId(Long userId, Pageable pageable);
}
