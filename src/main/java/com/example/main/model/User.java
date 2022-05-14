package com.example.main.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.NaturalId;

import javax.persistence.*;
import javax.validation.constraints.Size;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NaturalId
    @Column(name = "email",nullable = false)
    private String email;

    @Size(min = 5, message = "Length must be more than 5")
    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "phone", nullable = false)
    private String phone;

    @Column(name = "address", nullable = false)
    private String address;

    @Column(name = "active",nullable = false)
    private boolean active;

    @Column(name = "role",nullable = false)
    private String role = "Customer";

    @OneToOne(mappedBy = "user" , cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonIgnore //fix looping error
    private Cart cart;

}
