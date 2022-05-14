package com.example.main.service.impl;

import com.example.main.model.Cart;
import com.example.main.model.ProductOrder;
import com.example.main.model.User;
import com.example.main.repository.ProductOrderRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit4.SpringRunner;
import static org.mockito.Mockito.*;
import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.assertThat;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
public class ProductOrderServiceImplTest {

    @Mock
    private ProductOrderRepository productOrderRepository;

    @InjectMocks
    private ProductOrderServiceImpl productOrderService;

    private User user;

    private ProductOrder productOrder;

    @Before
    public void setUp() {
        user = new User();
        Cart cart = new Cart();

        productOrder = new ProductOrder();
        productOrder.setProductId("1");

        Set set = new HashSet<>();
        set.add(productOrder);

        cart.setProducts(set);

        user.setCart(cart);
    }

    @Test
    public void updateProductTest() {
        productOrderService.updateProduct("1",10,user);

        verify(productOrderRepository,times(1)).save(productOrder);


    }

    @Test
    public void findOneTest() {
        ProductOrder pOrder = productOrderService.findOne("1",user);

        assertSame(pOrder.getProductId(), productOrder.getProductId());
//        var a = pOrder.getProductId();
//        var b = productOrder.getProductId();
//        System.out.println(a);
//        System.out.println(b);
    }
}