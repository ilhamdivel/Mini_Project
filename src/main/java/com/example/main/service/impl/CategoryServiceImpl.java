package com.example.main.service.impl;

import com.example.main.enums.ResultEnum;
import com.example.main.exception.Exception;
import com.example.main.model.Category;
import com.example.main.repository.CategoryRepository;
import com.example.main.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class CategoryServiceImpl implements CategoryService {
    @Autowired
    CategoryRepository categoryRepository;


    @Override
    public List<Category> findAllCategory() {
        return categoryRepository.findAllByOrderByCategoryType();
    }

    @Override
    public Category findByCategoryType(Integer categoryType) {
        Category category = categoryRepository.findByCategoryType(categoryType);
        if(category == null) throw new Exception(ResultEnum.CATEGORY_NOT_FOUND);
        return category;
    }

    @Override
    @Transactional
    public Category createCategory(Category category) {
        return categoryRepository.save(category);
    }

    @Override
    @Transactional
    public Category updateCategory(Category category) {
        Category existingCategory = findByCategoryType(category.getCategoryType());
        if (existingCategory == null) throw new Exception(ResultEnum.CATEGORY_NOT_FOUND);
        existingCategory.setCategoryName(category.getCategoryName());
        return categoryRepository.save(existingCategory);

    }

    @Override
    @Transactional
    public void deleteCategory(Integer categoryType) {
        Category category = findByCategoryType(categoryType);
        if (category == null) throw new Exception(ResultEnum.CATEGORY_NOT_FOUND);
        categoryRepository.deleteCategoryByCategoryType(categoryType);
    }
}







