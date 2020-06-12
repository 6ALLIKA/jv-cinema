package com.dev.cinema.service;

import com.dev.cinema.model.Order;
import java.util.List;

public interface OrderService {
    Order completeOrder(Order order);

    List<Order> getOrderHistory(Long userId);

    Order getById(Long id);
}
