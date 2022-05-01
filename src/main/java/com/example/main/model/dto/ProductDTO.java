package com.example.main.model.dto;

import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;

@Data
public class ProductDTO {

    @NotEmpty
    private String productId;

    @Min(value = 1)
    private Integer quantity;

}
