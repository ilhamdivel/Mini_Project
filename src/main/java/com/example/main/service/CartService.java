package com.example.main.service;

import com.example.main.model.Cart;
import com.example.main.model.ProductOrder;
import com.example.main.model.User;

import java.util.Collection;

public interface CartService {
    Cart getCart(User user);

    void mergeLocalCart(Collection<ProductOrder> productOrders, User user);

    void deleteItem(String itemId, User user);

    void checkout (User user);
}
