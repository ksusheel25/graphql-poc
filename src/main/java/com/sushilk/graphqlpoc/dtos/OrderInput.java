package com.sushilk.graphqlpoc.dtos;

public record OrderInput
        (
                String productName,
                String address,
                int quantity,
                Double price,
                String status,
                Long userId
        ) { }
