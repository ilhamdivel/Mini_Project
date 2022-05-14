package com.example.main.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@DynamicUpdate
@Table(name = "M_Order")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long orderId;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY,mappedBy = "order")
    private Set<ProductOrder> products = new HashSet<>();

    @Column(name = "customer_email",nullable = false)
    private String customerEmail;

    @Column(name = "customer_name",nullable = false)
    private String customerName;

    @Column(name = "customer_phone",nullable = false)
    private String customerPhone;

    @Column(name = "customer_address",nullable = false)
    private String customerAddress;

    @Column(name = "order_amount",nullable = false)
    private BigDecimal orderAmount;

    //default 0 : new order
    @ColumnDefault("0")
    @Column(name = "order_status")
    private Integer orderStatus;

    @CreationTimestamp
    @Column(name = "created_at",nullable = false,updatable = false)
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at",nullable = false)
    private LocalDateTime updatedAt;

    public Order(User customer) {
        this.customerEmail = customer.getEmail();
        this.customerName = customer.getName();
        this.customerPhone = customer.getPhone();
        this.customerAddress = customer.getAddress();
        this.orderAmount = customer.getCart().getProducts().stream().map(item -> item.getProductPrice().multiply(new BigDecimal(item.getCount())))
                .reduce(BigDecimal::add)
                .orElse(new BigDecimal(0));
        this.orderStatus = 0;
    }

}













