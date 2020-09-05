package com.sda.eshop.ui;

import com.sda.eshop.dao.OrderDao;

public class OrderService {

    private OrderDao orderDao;

    public OrderService(OrderDao orderDao) {
        this.orderDao = orderDao;
    }
}
