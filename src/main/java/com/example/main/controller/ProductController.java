package com.example.main.controller;

import com.example.main.model.Product;
import com.example.main.service.CategoryService;
import com.example.main.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
public class ProductController {
    @Autowired
    CategoryService categoryService;

    @Autowired
    ProductService productService;

    @GetMapping("/product")
    public Page<Product> findAll(@RequestParam(value = "page", defaultValue = "1")Integer page,
                                 @RequestParam(value = "size",defaultValue = "3")Integer size) {
        PageRequest request = PageRequest.of(page-1,size);
        return productService.findAll(request);
    }

    @GetMapping("/product/{productId}")
    public ResponseEntity<Product> findOne(@PathVariable String productId){
        try {
            return ResponseEntity.ok(productService.findOne(productId));
        } catch (Exception e){
            return ResponseEntity.badRequest().build();
        }
    }

    @PostMapping("/admin/product/new")
    public ResponseEntity createProduct(@Valid @RequestBody Product product, BindingResult bindingResult){
        Product productId = productService.findOne(product.getProductId());
        if (productId != null) {
            bindingResult
                    .rejectValue("productId","error.product","There is already a product with that id");
        }
        if (bindingResult.hasErrors()){
            return ResponseEntity.badRequest().body(bindingResult);
        }
        return ResponseEntity.ok(productService.createProduct(product));
    }

    @PutMapping("/admin/product/{productId}/edit")
    public ResponseEntity editProduct(@PathVariable String productId,
                                      @Valid @RequestBody Product product,
                                      BindingResult bindingResult){
        if (bindingResult.hasErrors()){
            return ResponseEntity.badRequest().body(bindingResult);
        }
        if (!productId.equals(product.getProductId())){
            return ResponseEntity.badRequest().body("Id Not Same");
        }

        return ResponseEntity.ok(productService.updateProduct(product));
    }

    @DeleteMapping("/admin/product/{productId}/delete")
    public ResponseEntity deleteProduct(@PathVariable String productId){
        productService.deleteProduct(productId);
        return ResponseEntity.ok().build();
    }
}
