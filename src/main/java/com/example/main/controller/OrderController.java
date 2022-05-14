package com.example.main.controller;

import com.example.main.model.Order;
import com.example.main.service.OrderService;
import com.example.main.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class OrderController {
    @Autowired
    OrderService orderService;

    @Autowired
    UserService userService;

    @GetMapping("/order")
    public Page<Order> findOrderList(@RequestParam(value = "page", defaultValue = "1")Integer page,
                                     @RequestParam(value = "size", defaultValue = "10")Integer size) {
        PageRequest request = PageRequest.of(page - 1, size);
        Page<Order> orders;


         // if role customer show order by customer email
         // if role not customer show all order ( admin )
        orders = orderService.findAllOrder(request);
        return orders;
    }

    @PatchMapping("/order/finish/{id}")
    public ResponseEntity<Order> finish(@PathVariable("id") Long orderId){
        // if role customer --> unauthorized
        return ResponseEntity.ok(orderService.finish(orderId));
    }

    @PatchMapping("order/cancel/{id}")
    public ResponseEntity<Order> cancel(@PathVariable("id") Long orderId){
        // if role customer --> unauthorized
        return ResponseEntity.ok(orderService.cancel(orderId));
    }

    @GetMapping("order/{id}")
    public ResponseEntity showOne(@PathVariable("id") Long orderId){
        // if role customer --> unauthorized
        Order order = orderService.findOne(orderId);
        return ResponseEntity.ok(order);
    }
}

















