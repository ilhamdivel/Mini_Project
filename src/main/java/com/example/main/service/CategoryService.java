package com.example.main.service;

import com.example.main.model.Category;

import java.util.List;
import java.util.Optional;

public interface CategoryService {
    List<Category> findAllCategory();

    //find products by category type
    Category findByCategoryType(Integer categoryType);

    Category createCategory(Category category);

    Category updateCategory(Category category);

    void deleteCategory(Integer categoryType);
}
