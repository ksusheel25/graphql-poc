package com.sushilk.graphqlpoc.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String productName;
    private String address;
    private Integer quantity;
    private Double price;
    private String status;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
}

