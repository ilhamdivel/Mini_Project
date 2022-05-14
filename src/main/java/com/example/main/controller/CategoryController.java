package com.example.main.controller;

import com.example.main.model.Category;
import com.example.main.model.Product;
import com.example.main.model.dto.CategoryDTO;
import com.example.main.service.CategoryService;
import com.example.main.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CategoryController {
    @Autowired
    CategoryService categoryService;

    @Autowired
    ProductService productService;

    @PostMapping("/admin/category/new")
    public ResponseEntity<Category> createCategory(@RequestBody Category category) {
        try {
            return ResponseEntity.ok(categoryService.createCategory(category));
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/category/all")
    public ResponseEntity<List<Category>> findAllCategory() {
        try {
            return ResponseEntity.ok(categoryService.findAllCategory());
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }


    // View Products In Category
    @GetMapping("/product/category/{categoryType}")
    public CategoryDTO showProducts(@PathVariable Integer categoryType,
                                    @RequestParam(value = "page", defaultValue = "1")Integer page,
                                    @RequestParam(value = "size", defaultValue = "3")Integer size){
        Category category = categoryService.findByCategoryType(categoryType);
        PageRequest request = PageRequest.of(page - 1,size);
        Page<Product> productInCategory = productService.findAllInCategory(categoryType, request);
        var dto = new CategoryDTO("", productInCategory);
        dto.setCategory(category.getCategoryName());
        return dto;
    }

    @PutMapping("/admin/category/{categoryType}/edit")
    public ResponseEntity<Category> updateCategory(@RequestBody Category category, @PathVariable Integer categoryType){
        try {
            return ResponseEntity.ok(categoryService.updateCategory(category));
        } catch (Exception e){
            return ResponseEntity.badRequest().build();
        }
    }

    @DeleteMapping("/admin/category/{categoryType}/delete")
    public ResponseEntity deleteCategory(@PathVariable Integer categoryType){
        categoryService.deleteCategory(categoryType);
        return ResponseEntity.ok().build();
    }


}
