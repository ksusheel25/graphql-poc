package com.sushilk.graphqlpoc.dtos;

public record UserInput(
        String name,
        String email,
        String phone,
        String city) {
}
