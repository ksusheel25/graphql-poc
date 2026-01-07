package com.sushilk.graphqlpoc.repositories;

import com.sushilk.graphqlpoc.entities.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
}
