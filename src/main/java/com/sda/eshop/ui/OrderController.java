package com.sda.eshop.ui;

import com.sda.eshop.model.Order;
import com.sda.eshop.model.User;

import java.util.List;

public class OrderController {

    private OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    public void save(Order order) {

    }

    public List<Order> findByUser(User loggedUser) {
        return null;
    }
}
