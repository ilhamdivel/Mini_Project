package com.example.main.service;

import com.example.main.model.ProductOrder;
import com.example.main.model.User;

public interface ProductOrderService {
    //update product in cart
    void updateProduct(String itemId, Integer quantity, User user);

    //show product order after update product
    ProductOrder findOne(String itemId, User user);
}
