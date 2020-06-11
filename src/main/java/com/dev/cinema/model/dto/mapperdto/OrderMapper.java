package com.dev.cinema.model.dto.mapperdto;

import com.dev.cinema.model.Order;
import com.dev.cinema.model.Ticket;
import com.dev.cinema.model.dto.order.OrderRequestDto;
import com.dev.cinema.model.dto.order.OrderResponseDto;
import com.dev.cinema.service.ShoppingCartService;
import com.dev.cinema.service.UserService;
import java.time.LocalDateTime;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class OrderMapper {
    @Autowired
    private UserService userService;
    @Autowired
    private ShoppingCartService shoppingCartService;

    public OrderResponseDto getOrderResponce(Order order) {
        OrderResponseDto dto = new OrderResponseDto();
        dto.setOrderDate(order.getTime().toString());
        dto.setUserId(order.getUser().getId());
        dto.setTicketIds(order.getTickets()
                .stream()
                .map(Ticket::getId)
                .collect(Collectors.toList()));
        return dto;
    }

    public Order getOrderFromRequest(OrderRequestDto dto) {
        Order order = new Order();
        order.setTime(LocalDateTime.now());
        order.setUser(userService.getById(dto.getUserId()));
        order.setTickets(shoppingCartService
                .getByUserId(order.getUser().getId())
                .getTickets());
        return order;
    }
}
