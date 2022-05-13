package com.example.main.repository;

import com.example.main.model.Order;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
    Order findByOrderId(Long orderId);

    //find by status
    Page<Order> findAllByOrderStatusOrderByCreatedAtDesc(Integer orderStatus, Pageable pageable);

    //find by customer email
    Page<Order> findAllByCustomerEmailOrderByOrderStatusAscCreatedAtDesc(String customerEmail, Pageable pageable);

    //find all order
    Page<Order> findAllByOrderByOrderStatusAscCreatedAtDesc(Pageable pageable);

    //find by customer phone
    Page<Order> findAllByCustomerPhoneOrderByOrderStatusAscCreatedAtDesc(String customerPhone, Pageable pageable);

}
