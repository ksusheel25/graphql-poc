package com.sushilk.graphqlpoc.services;

import com.sushilk.graphqlpoc.entities.Order;
import com.sushilk.graphqlpoc.repositories.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final OrderRepository orderRepository;

    // Add order-related methods here
    // 1. Create Order
    public Order createOrder(Order order) {
        return orderRepository.save(order);
    }
    // 2. Get All Orders
    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }
    // 3. Get Order by ID
    public Order getOrderById(Long id) {
        return orderRepository.findById(id).orElseThrow(() -> new RuntimeException("Order not found"));
    }
    // 4. Update Order
    public Order updateOrder(Long id, Order orderDetails) {
        Order order = orderRepository.findById(id).orElseThrow(() -> new RuntimeException("Order not found"));
        order.setProductName(orderDetails.getProductName());
        order.setAddress(orderDetails.getAddress());
        order.setQuantity(orderDetails.getQuantity());
        order.setPrice(orderDetails.getPrice());
        order.setUser(orderDetails.getUser());
        return orderRepository.save(order);
    }
    // 5. Delete Order
    public boolean deleteOrder(Long id) {
        Order order = orderRepository.findById(id).orElseThrow(() -> new RuntimeException("Order not found"));
        orderRepository.delete(order);
        return true;
    }
}
