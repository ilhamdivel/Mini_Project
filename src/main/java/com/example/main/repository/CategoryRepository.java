package com.example.main.repository;

import com.example.main.model.Category;
import com.example.main.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CategoryRepository extends JpaRepository<Category, Long> {

    //list category
    List<Category> findByCategoryTypeInOrderByCategoryTypeAsc(List<Integer> categoryTypes);

    //all category
    List<Category> findAllByOrderByCategoryType();

    //one category
    Category findByCategoryType(Integer categoryType);
}
