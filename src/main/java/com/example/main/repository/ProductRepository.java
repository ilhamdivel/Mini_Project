package com.example.main.repository;

import com.example.main.model.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, String> {

    Product findByProductId(String id);

    Page<Product> findAllByProductStatusOrderByProductIdAsc(Integer productStatus, Pageable pageable);

    Page<Product> findAllByCategoryTypeOrderByProductIdAsc(Integer categoryType, Pageable pageable);

    Page<Product> findAllByOrderByProductId(Pageable pageable);
}
