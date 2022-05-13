package com.example.main.service.impl;

import com.example.main.model.Product;
import com.example.main.repository.ProductRepository;
import com.example.main.service.CategoryService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.junit4.SpringRunner;

import static org.mockito.Mockito.*;

@RunWith(SpringRunner.class)
public class ProductServiceImplTest {

    @InjectMocks
    private ProductServiceImpl productService;

    @Mock
    private ProductRepository productRepository;

    @Mock
    private CategoryService categoryService;

    private Product product;

    @Before
    public void setUp() {
        product = new Product();
        product.setProductId("1");
        product.setProductStock(10);
        product.setProductStatus(1);
    }

    @Test
    public void findOne() {
        //stubbing
        when(productRepository.findByProductId(product.getProductId())).thenReturn(product);

        productService.findOne(product.getProductId());

        //verify
        verify(productRepository,times(1)).findByProductId(product.getProductId());

    }

    @Test
    public void findAll() {

    }

    @Test
    public void findAllInCategory() {
    }

    @Test
    public void increaseStockTest() {
        when(productRepository.findByProductId(product.getProductId())).thenReturn(product);

        productService.increaseStock("1",5);

        verify(productRepository, times(1)).save(product);
    }

    @Test
    public void decreaseStock() {
        when(productRepository.findByProductId(product.getProductId())).thenReturn(product);

        productService.decreaseStock("1",2);

        verify(productRepository,times(1)).save(product);
    }

    @Test
    public void updateProduct() {
        productService.updateProduct(product);

        verify(productRepository,times(1)).save(product);
    }

    @Test
    public void createProduct() {

    }

    @Test
    public void deleteProduct() {
        when(productRepository.findByProductId(product.getProductId())).thenReturn(product);

        productService.deleteProduct("1");

        verify(productRepository, times(1)).delete(product);
    }
}