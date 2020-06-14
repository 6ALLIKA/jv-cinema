package com.dev.cinema.controller;

import com.dev.cinema.model.Order;
import com.dev.cinema.model.ShoppingCart;
import com.dev.cinema.model.User;
import com.dev.cinema.model.dto.mapperdto.OrderMapper;
import com.dev.cinema.model.dto.order.OrderResponseDto;
import com.dev.cinema.service.OrderService;
import com.dev.cinema.service.ShoppingCartService;
import com.dev.cinema.service.UserService;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/order")
public class OrderController {
    @Autowired
    private OrderService orderService;
    @Autowired
    private OrderMapper orderMapper;
    @Autowired
    private UserService userService;
    @Autowired
    private ShoppingCartService shoppingCartService;

    @PostMapping
    public String add(Authentication authentication) {
        User user = userService.findByEmail(authentication.getName());
        ShoppingCart shoppingCart = shoppingCartService.getByUser(user);
        Order order = new Order();
        order.setTickets(shoppingCart.getTickets());
        order.setUser(user);
        orderService.completeOrder(order);
        return "Order created successful";
    }

    @GetMapping("/user/history")
    public List<OrderResponseDto> getAll(Authentication authentication) {
        User user = userService.findByEmail(authentication.getName());
        return orderService.getOrderHistory(user)
                .stream()
                .map(orderMapper::getOrderResponse)
                .collect(Collectors.toList());
    }
}
