package com.example.main.service.impl;

import com.example.main.enums.ProductStatusEnum;
import com.example.main.enums.ResultEnum;
import com.example.main.exception.Exception;
import com.example.main.model.Product;
import com.example.main.repository.ProductRepository;
import com.example.main.service.CategoryService;
import com.example.main.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    ProductRepository productRepository;

    @Autowired
    CategoryService categoryService;


    @Override
    public Product findOne(String productId) {
        return productRepository.findByProductId(productId);
    }


    @Override
    public Page<Product> findAll(Pageable pageable) {
        return productRepository.findAllByOrderByProductId(pageable);
    }


    @Override
    public Page<Product> findAllInCategory(Integer categoryType, Pageable pageable) {
        return productRepository.findAllByCategoryTypeOrderByProductIdAsc(categoryType, pageable);
    }

    @Override
    @Transactional
    public void increaseStock(String productId, int amount) {
        Product product = findOne(productId);
        if (product == null) throw new Exception(ResultEnum.PRODUCT_NOT_EXIST);

        int update = product.getProductStock() + amount;
        product.setProductStock(update);
        productRepository.save(product);
    }

    @Override
    @Transactional
    public void decreaseStock(String productId, int amount) {
        Product product = findOne(productId);
        if (product == null) throw new Exception(ResultEnum.PRODUCT_NOT_EXIST);

        int update = product.getProductStock() - amount;
        if(update <= 0) throw new Exception(ResultEnum.PRODUCT_NOT_ENOUGH);

        product.setProductStock(update);
        productRepository.save(product);
    }

    @Override
    public Product updateProduct(Product product) {
        categoryService.findByCategoryType(product.getCategoryType());

        if (product.getProductStatus() > 1) {
            throw new Exception(ResultEnum.PRODUCT_STATUS_ERROR);
        }

        return productRepository.save(product);
    }

    @Override
    public Product createProduct(Product product) {
        return updateProduct(product);
    }

    @Override
    public void deleteProduct(String productId) {
        Product product = findOne(productId);

        if (product == null ) throw new Exception(ResultEnum.PRODUCT_NOT_EXIST);

        productRepository.delete(product);

    }
}
