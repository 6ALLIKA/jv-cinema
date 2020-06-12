package com.dev.cinema.service.impl;

import com.dev.cinema.dao.OrderDao;
import com.dev.cinema.model.Order;
import com.dev.cinema.model.ShoppingCart;
import com.dev.cinema.service.OrderService;
import com.dev.cinema.service.ShoppingCartService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderServiceImpl implements OrderService {
    private final OrderDao orderDao;
    private final ShoppingCartService shoppingCartService;

    @Autowired
    public OrderServiceImpl(OrderDao orderDao, ShoppingCartService shoppingCartService) {
        this.orderDao = orderDao;
        this.shoppingCartService = shoppingCartService;
    }

    @Override
    public Order completeOrder(Order order) {
        ShoppingCart userShoppingCart = shoppingCartService.getByUserId(order.getUser().getId());
        shoppingCartService.clear(userShoppingCart);
        return orderDao.add(order);
    }

    @Override
    public List<Order> getOrderHistory(Long userId) {
        return orderDao.getOrdersByUserId(userId);
    }

    @Override
    public Order getById(Long id) {
        return orderDao.getById(id);
    }
}
