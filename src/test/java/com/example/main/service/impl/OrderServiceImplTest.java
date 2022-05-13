package com.example.main.service.impl;

import com.example.main.enums.OrderStatusEnum;
import com.example.main.model.Order;
import com.example.main.model.Product;
import com.example.main.model.ProductOrder;
import com.example.main.repository.OrderRepository;
import com.example.main.repository.ProductRepository;
import com.example.main.service.ProductService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashSet;
import java.util.Set;

import static org.mockito.Mockito.*;


import static org.junit.jupiter.api.Assertions.*;
@RunWith(SpringRunner.class)
public class OrderServiceImplTest {
    @Mock
    private OrderRepository orderRepository;

    @Mock
    private ProductRepository productRepository;

    @Mock
    private ProductService productService;

    @InjectMocks
    private OrderServiceImpl orderService;

    private Order order;

    private Product product;

    @Before
    public void setUp() {
        order = new Order();
        order.setOrderId(1L);
        order.setOrderStatus(OrderStatusEnum.NEW.getCode());

        ProductOrder productOrder = new ProductOrder();
        productOrder.setProductId("1");
        productOrder.setCount(10);

        Set<ProductOrder> set = new HashSet<>();
        set.add(productOrder);

        order.setProducts(set);

        product = new Product();
        product.setProductStock(10);
    }

    @Test
    public void findAllOrder() {
    }

    @Test
    public void findOne() {
    }

    @Test
    public void finish() {
        when(orderRepository.findByOrderId(order.getOrderId())).thenReturn(order);

        Order mOrder = orderService.finish(order.getOrderId());

        assertSame(mOrder.getOrderId(), order.getOrderId());
        assertSame(mOrder.getOrderStatus(), OrderStatusEnum.FINISHED.getCode());
    }

    @Test
    public void cancel() {
    }
}