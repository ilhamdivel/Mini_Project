package com.example.main.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.NaturalId;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
@DynamicUpdate
@AllArgsConstructor
@NoArgsConstructor
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer categoryId;

    @Column(name = "category_name",nullable = false)
    private String categoryName;

    @NaturalId
    @Column(name = "category_type",nullable = false)
    private Integer categoryType;

    @CreationTimestamp
    @Column(name = "created_at",nullable = false,updatable = false)
    private Date createAt;

    @UpdateTimestamp
    @Column(name = "updated_at",nullable = false)
    private Date updateAt;
}
