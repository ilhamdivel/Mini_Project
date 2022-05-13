package com.example.main.controller;

import com.example.main.model.Cart;
import com.example.main.model.ProductOrder;
import com.example.main.model.User;
import com.example.main.model.dto.ProductDTO;
import com.example.main.repository.ProductOrderRepository;
import com.example.main.service.CartService;
import com.example.main.service.ProductOrderService;
import com.example.main.service.ProductService;
import com.example.main.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.Collection;
import java.util.Collections;

@RestController
public class CartController {
    @Autowired
    CartService cartService;

    @Autowired
    UserService userService;

    @Autowired
    ProductService productService;

    @Autowired
    ProductOrderService productOrderService;

    @Autowired
    ProductOrderRepository productOrderRepository;


    @PostMapping("/cart")
    public ResponseEntity<Cart> mergeCart(@RequestBody Collection<ProductOrder> productOrders, Principal principal){
        User user = userService.findOne(principal.getName());
        try {
            cartService.mergeLocalCart(productOrders, user);
        } catch (Exception e){
            ResponseEntity.badRequest().body("Merge Cart Failed");
        }
        return ResponseEntity.ok(cartService.getCart(user));
    }
    @GetMapping("/cart")
    public Cart findCart(Principal principal) {
        User user = userService.findOne(principal.getName());
        return cartService.getCart(user);
    }

    @PostMapping("/cart/add")
    public boolean addToCart(@RequestBody ProductDTO productDTO, Principal principal) {
        var product = productService.findOne(productDTO.getProductId());
        try {
            mergeCart(Collections.singleton(new ProductOrder(product, productDTO.getQuantity())),principal);
        } catch (Exception e){
            return false;
        }
        return true;
    }

    @PutMapping("/cart/{itemId}")
    public ProductOrder modifyItem(@PathVariable String itemId, @RequestBody Integer quantity, Principal principal){
        User user = userService.findOne(principal.getName());
        productOrderService.updateProduct(itemId,quantity,user);
        return productOrderService.findOne(itemId,user);
    }

    @PostMapping("/cart/checkout")
    public ResponseEntity checkout(Principal principal) {
        User user = userService.findOne(principal.getName()); // email = username
        cartService.checkout(user);
        return ResponseEntity.ok(null);
    }

    @DeleteMapping("/cart/{itemId}")
    public void deleteItem(@PathVariable String itemId, Principal principal) {
        User user = userService.findOne(principal.getName());
        cartService.deleteItem(itemId,user) ;
    }


}



















