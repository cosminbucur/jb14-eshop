package com.sda.eshop.ui;

import com.sda.eshop.model.Order;
import com.sda.eshop.model.User;

import java.util.List;

public class OrderController {

    private OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    public Order save(Order order) {
        return orderService.save(order);
    }

    public List<Order> findByUser(User loggedUser) {
        return null;
    }
}
