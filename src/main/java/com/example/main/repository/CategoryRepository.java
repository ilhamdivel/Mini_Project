package com.example.main.repository;

import com.example.main.model.Category;
import com.example.main.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CategoryRepository extends JpaRepository<Category, Integer> {
    //find all category
    List<Category> findAllByOrderByCategoryType();

    //find product by category type
    Category findByCategoryType(Integer categoryType);

    //delete by category type
    void deleteCategoryByCategoryType(Integer categoryType);
}
