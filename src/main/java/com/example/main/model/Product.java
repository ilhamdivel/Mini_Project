package com.example.main.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.Min;
import java.math.BigDecimal;
import java.util.Date;

@Entity
@Data
@DynamicUpdate
@AllArgsConstructor
@NoArgsConstructor
public class Product {
    @Id
    private String productId;

    @Column(name = "product_name",nullable = false)
    private String productName;

    @Column(name = "product_price",nullable = false)
    private BigDecimal productPrice;


    @Min(0)
    @Column(name = "product_stock",nullable = false)
    private Integer productStock;

    @Column(name = "product_description",nullable = false)
    private String productDescription;

    @Column(name = "product_image",nullable = false)
    private String productImage;

    /** 0:Ready ---- 1:Not Ready */
    @ColumnDefault("0")
    @Column(name = "product_status")
    private Integer productStatus;

    @ColumnDefault("0")
    @Column(name = "category_type")
    private Integer categoryType;

    @CreationTimestamp
    private Date createAt;

    @UpdateTimestamp
    private Date updateAt;


}
