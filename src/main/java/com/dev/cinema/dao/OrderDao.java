package com.dev.cinema.dao;

import com.dev.cinema.model.Order;
import java.util.List;

public interface OrderDao {
    Order add(Order order);

    List<Order> getOrdersByUserId(Long userId);

    Order getById(Long id);
}
