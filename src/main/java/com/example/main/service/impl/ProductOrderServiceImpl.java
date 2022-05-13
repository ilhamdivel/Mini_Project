package com.example.main.service.impl;


import com.example.main.model.ProductOrder;
import com.example.main.model.User;
import com.example.main.repository.ProductOrderRepository;
import com.example.main.service.ProductOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.concurrent.atomic.AtomicReference;

@Service
public class ProductOrderServiceImpl implements ProductOrderService {

    @Autowired
    ProductOrderRepository productOrderRepository;


    @Override
    @Transactional
    public void updateProduct(String itemId, Integer quantity, User user) {
        var productOrder = user.getCart().getProducts().stream().filter(e -> itemId.equals(e.getProductId())).findFirst();
        productOrder.ifPresent(product ->{
            product.setCount(quantity);
            productOrderRepository.save(product);
        });
    }

    @Override
    public ProductOrder findOne(String itemId, User user) {
        var productOrder = user.getCart().getProducts().stream().filter(e -> itemId.equals(e.getProductId())).findFirst();
        AtomicReference<ProductOrder> res = new AtomicReference<>();
        productOrder.ifPresent(res::set);
        return res.get();
    }
}
