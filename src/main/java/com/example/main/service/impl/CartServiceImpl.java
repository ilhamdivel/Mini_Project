package com.example.main.service.impl;

import com.example.main.enums.ResultEnum;
import com.example.main.exception.Exception;
import com.example.main.model.Cart;
import com.example.main.model.Order;
import com.example.main.model.ProductOrder;
import com.example.main.model.User;
import com.example.main.repository.CartRepository;
import com.example.main.repository.OrderRepository;
import com.example.main.repository.ProductOrderRepository;
import com.example.main.repository.UserRepository;
import com.example.main.service.CartService;
import com.example.main.service.ProductService;
import com.example.main.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Collection;
import java.util.Optional;
import java.util.Set;

@Service
public class CartServiceImpl implements CartService {
    @Autowired
    ProductService productService;

    @Autowired
    OrderRepository orderRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    ProductOrderRepository productOrderRepository;

    @Autowired
    CartRepository cartRepository;

    @Autowired
    UserService userService;

    @Override
    public Cart getCart(User user) {
        return user.getCart();
    }

    @Override
    public void mergeLocalCart(Collection<ProductOrder> productOrders, User user) {
        Cart finalCart = user.getCart();
        productOrders.forEach(productOrder -> {
            Set<ProductOrder> set = finalCart.getProducts();
            Optional<ProductOrder> old = set.stream().filter(e -> e.getProductId().equals(productOrder.getProductId())).findFirst();
            ProductOrder product;
            if (old.isPresent()) {
                product = old.get();
                product.setCount(productOrder.getCount() + product.getCount());
            } else {
                product = productOrder;
                product.setCart(finalCart);
                finalCart.getProducts().add(product);
            }
            productOrderRepository.save(product);
        });
        cartRepository.save(finalCart);
    }

    @Override
    @Transactional
    public void deleteItem(String itemId, User user) {
        if (itemId.equals("") || user == null ) {
            throw new Exception(ResultEnum.ORDER_STATUS_ERROR);
        }

        var productOrder = user.getCart().getProducts().stream().filter(e -> itemId.equals(e.getProductId())).findFirst();
        productOrder.ifPresent(order -> {
            order.setCart(null);
            productOrderRepository.deleteById(order.getId());
        });
    }

    @Override
    @Transactional
    public void checkout(User user) {
        Order order = new Order(user);
        orderRepository.save(order);

        //decrease stock after checkout
        user.getCart().getProducts().forEach(productOrder -> {
            productOrder.setCart(null);
            productOrder.setOrder(order);
            productService.decreaseStock(productOrder.getProductId(),productOrder.getCount());
            productOrderRepository.save(productOrder);
        });
    }
}







