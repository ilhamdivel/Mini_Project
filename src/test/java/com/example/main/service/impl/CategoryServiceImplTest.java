package com.example.main.service.impl;

import com.example.main.model.Cart;
import com.example.main.model.Category;
import com.example.main.model.ProductOrder;
import com.example.main.model.User;
import com.example.main.repository.CategoryRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import static org.mockito.Mockito.*;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
public class CategoryServiceImplTest {

    @InjectMocks
    private CategoryServiceImpl categoryService;

    @Mock
    private CategoryRepository categoryRepository;

    @Before
    public void setUp() {

    }

    @Test
    public void findAllCategory() {

    }

    @Test
    public void findByCategoryType() {
        Category category = new Category();
        category.setCategoryId(1);

        when(categoryRepository.findByCategoryType(category.getCategoryId())).thenReturn(category);

        categoryService.findByCategoryType(category.getCategoryId());

        verify(categoryRepository, times(1)).findByCategoryType(category.getCategoryId());
    }

    @Test
    public void createCategory() {
    }

    @Test
    public void updateCategoryTest() {
    }

    @Test
    public void deleteCategoryTest() {
    }
}