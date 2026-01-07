package com.sushilk.graphqlpoc.controllers;

import com.sushilk.graphqlpoc.dtos.OrderInput;
import com.sushilk.graphqlpoc.entities.Order;
import com.sushilk.graphqlpoc.entities.User;
import com.sushilk.graphqlpoc.services.OrderService;
import com.sushilk.graphqlpoc.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;
    private final UserService userService;

    //create order
    @MutationMapping(name = "createOrder")
    public Order createOrder(@Argument("input") OrderInput order) {
        User user = userService.getUserById(order.userId());
        Order newOrder = new Order();
        newOrder.setProductName(order.productName());
        newOrder.setQuantity(order.quantity());
        newOrder.setPrice(order.price());
        newOrder.setAddress(order.address());
        newOrder.setStatus(order.status());
        newOrder.setUser(user);
        return orderService.createOrder(newOrder);
    }

    //get all orders
    @QueryMapping(name = "getAllOrders")
    public List<Order> getAllOrders() {
        return orderService.getAllOrders();
    }

    //get order by id
    @QueryMapping(name = "getOrderById")
    public Order getOrderById(@Argument Long id) {
        return orderService.getOrderById(id);
    }

    //update order
    @MutationMapping(name = "updateOrder")
    public Order updateOrder(@Argument Long id, @Argument("input") OrderInput orderInput) {
        User user = userService.getUserById(orderInput.userId());
        Order orderDetails = new Order();
        orderDetails.setProductName(orderInput.productName());
        orderDetails.setQuantity(orderInput.quantity());
        orderDetails.setPrice(orderInput.price());
        orderDetails.setStatus(orderInput.status());
        orderDetails.setAddress(orderInput.address());
        orderDetails.setUser(user);
        return orderService.updateOrder(id, orderDetails);
    }

    //delete order
    @MutationMapping(name = "deleteOrder")
    public boolean deleteOrder(@Argument Long id) {
        return orderService.deleteOrder(id);
    }
}
