package com.example.main.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Min;
import java.math.BigDecimal;
import java.util.Objects;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductOrder {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    @JsonIgnore
    private Cart cart;


    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnore
    private Order order;

    @Column(name = "product_id",nullable = false)
    private String productId;

    @Column(name = "product_name",nullable = false)
    private String productName;

    @Column(name = "product_description",nullable = false)
    private String productDescription;

    @Column(name = "product_image",nullable = false)
    private String productImage;

    @Column(name = "category_type",nullable = false)
    private Integer categoryType;

    @Column(name = "product_price",nullable = false)
    private BigDecimal productPrice;

    @Min(0)
    @Column(name = "product_stock")
    private Integer productStock;

    @Min(1)
    @Column(name = "count")
    private Integer count;

    public ProductOrder(Product product, Integer quantity) {
        this.productId = product.getProductId();
        this.productName = product.getProductName();
        this.productDescription = product.getProductDescription();
        this.productImage = product.getProductImage();
        this.categoryType = product.getCategoryType();
        this.productPrice = product.getProductPrice();
        this.productStock = product.getProductStock();
        this.count = quantity;
    }
    @Override
    public int hashCode() {

        return Objects.hash(super.hashCode(), id, productId, productName, productDescription, productImage, categoryType, productPrice);
    }
}
