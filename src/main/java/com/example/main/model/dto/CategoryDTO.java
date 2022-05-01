package com.example.main.model.dto;

import com.example.main.model.Product;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.domain.Page;

@AllArgsConstructor
@Setter
@Getter
public class CategoryDTO {
    private String category;

    private Page<Product> page;


}
