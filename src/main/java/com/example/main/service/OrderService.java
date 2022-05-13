package com.example.main.service;

import com.example.main.model.Order;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface OrderService {
    Page<Order> findAllOrder(Pageable pageable);

    Order findOne(Long orderId);

    Order finish(Long orderId);

    Order cancel(Long orderId);
}
