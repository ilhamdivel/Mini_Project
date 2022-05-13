package com.example.main.service.impl;

import com.example.main.enums.OrderStatusEnum;
import com.example.main.enums.ResultEnum;
import com.example.main.exception.Exception;
import com.example.main.model.Order;
import com.example.main.model.Product;
import com.example.main.model.ProductOrder;
import com.example.main.repository.OrderRepository;
import com.example.main.repository.ProductOrderRepository;
import com.example.main.repository.ProductRepository;
import com.example.main.repository.UserRepository;
import com.example.main.service.OrderService;
import com.example.main.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    OrderRepository orderRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    ProductRepository productRepository;

    @Autowired
    ProductService productService;

    @Autowired
    ProductOrderRepository productOrderRepository;

    @Override
    public Page<Order> findAllOrder(Pageable pageable) {
        return orderRepository.findAllByOrderByOrderStatusAscCreatedAtDesc(pageable);
    }

    @Override
    public Order findOne(Long orderId) {
        Order order = orderRepository.findByOrderId(orderId);
        if(order == null){
            throw new Exception(ResultEnum.ORDER_NOT_FOUND);
        }
        return order;
    }

    @Override
    @Transactional
    public Order finish(Long orderId) {
        Order order = findOne(orderId);
        if (!order.getOrderStatus().equals(OrderStatusEnum.NEW.getCode())) {
            throw new Exception(ResultEnum.ORDER_STATUS_ERROR);
        }

        order.setOrderStatus(OrderStatusEnum.FINISHED.getCode());
        orderRepository.save(order);
        return orderRepository.findByOrderId(orderId);
    }

    @Override
    @Transactional
    public Order cancel(Long orderId) {
        Order order = findOne(orderId);
        if (!order.getOrderStatus().equals(OrderStatusEnum.NEW.getCode())) {
            throw new Exception(ResultEnum.ORDER_STATUS_ERROR);
        }

        order.setOrderStatus(OrderStatusEnum.CANCELED.getCode());
        orderRepository.save(order);

        //increase stock after cancel
        Iterable<ProductOrder> products = order.getProducts();
        for (ProductOrder productOrder : products) {
            Product product = productRepository.findByProductId(productOrder.getProductId());
            if (product != null) {
                productService.increaseStock(productOrder.getProductId(), productOrder.getCount());
            }
        }
        return orderRepository.findByOrderId(orderId);
    }
}
