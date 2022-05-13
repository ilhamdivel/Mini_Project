package com.example.main.service.impl;

import com.example.main.model.Cart;
import com.example.main.model.ProductOrder;
import com.example.main.model.User;
import com.example.main.repository.CartRepository;
import com.example.main.repository.OrderRepository;
import com.example.main.repository.ProductOrderRepository;
import com.example.main.service.ProductService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit4.SpringRunner;
import static org.mockito.Mockito.*;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
@RunWith(SpringRunner.class)
public class CartServiceImplTest {

    @InjectMocks
    private CartServiceImpl cartService;

    @Mock
    private ProductService productService;

    @Mock
    private ProductOrderRepository productOrderRepository;

    @Mock
    private CartRepository cartRepository;

    @Mock
    private OrderRepository orderRepository;

    private User user;

    private ProductOrder productOrder;

    private Set<ProductOrder> set;

    private Cart cart;

    @Before
    public void setUp() {
        user = new User();
        cart = new Cart();

        user.setEmail("ilham@gmail.com");
        user.setName("Ilham");
        user.setPhone("0821");
        user.setAddress("Jalan Tan");

        productOrder = new ProductOrder();
        productOrder.setProductId("1");
        productOrder.setCount(10);
        productOrder.setProductPrice(BigDecimal.valueOf(1));

        set = new HashSet<>();
        set.add(productOrder);

        cart.setProducts(set);

        user.setCart(cart);
    }

    @Test
    public void getCart() {
    }

    @Test
    public void mergeLocalCart() {
        cartService.mergeLocalCart(set,user);

        verify(cartRepository,times(1)).save(cart);
        verify(productOrderRepository,times(1)).save(productOrder);
    }

    @Test
    public void deleteItemTest() {
        cartService.deleteItem("1",user);

        verify(productOrderRepository,times(1)).deleteById(productOrder.getId());
    }

    @Test
    public void checkoutTest() {
        cartService.checkout(user);

        verify(productOrderRepository, times(1)).save(productOrder);
    }
}