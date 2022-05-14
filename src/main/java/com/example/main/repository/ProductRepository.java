package com.example.main.repository;

import com.example.main.model.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, String> {
    //find product by product id
    Product findByProductId(String id);

    //find all by category type
    Page<Product> findAllByCategoryTypeOrderByProductIdAsc(Integer categoryType, Pageable pageable);

    //find all by product id
    Page<Product> findAllByOrderByProductId(Pageable pageable);
}
