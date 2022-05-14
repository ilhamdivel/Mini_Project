package com.example.main.model.dto;

import lombok.Data;

import javax.validation.constraints.Min;

@Data
public class ProductDTO {

    private String productId;

    @Min(value = 1)
    private Integer quantity;

}
