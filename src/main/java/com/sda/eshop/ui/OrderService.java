package com.sda.eshop.ui;

import com.sda.eshop.dao.OrderDao;
import com.sda.eshop.model.Order;

public class OrderService {

    private OrderDao orderDao;

    public OrderService(OrderDao orderDao) {
        this.orderDao = orderDao;
    }

    public Order save(Order order) {
        return orderDao.save(order);
    }
}
