package com.example.main.repository;

import com.example.main.model.Order;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
    Order findByOrderId(Long orderId);

    Page<Order> findAllByOrderStatusOrderByCreatedAtDesc(Integer orderStatus, Pageable pageable);

    Page<Order> findAllByCustomerEmailOrderByOrderStatusAscCreatedAtDesc(String customerEmail, Pageable pageable);

    Page<Order> findAllByOrderByOrderStatusAscCreatedAtDesc(Pageable pageable);

    Page<Order> findAllByCustomerPhoneOrderByOrderStatusAscCreatedAtDesc(String customerPhone, Pageable pageable);
}
