package com.example.main.service;

import com.example.main.model.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ProductService {
    Product findOne(String productId);

    Page<Product> findAll(Pageable pageable);

    //find all products by category type
    Page<Product> findAllInCategory(Integer categoryType, Pageable pageable);

    //increase stock when cancel order
    void increaseStock(String productId, int amount);

    //decrease stock when checkout
    void decreaseStock(String productId, int amount);

    Product updateProduct(Product product);

    Product createProduct(Product product);

    void deleteProduct(String productId);


}
