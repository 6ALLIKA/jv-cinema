package com.dev.cinema.controller;

import com.dev.cinema.model.Order;
import com.dev.cinema.model.dto.mapperdto.OrderMapper;
import com.dev.cinema.model.dto.order.OrderRequestDto;
import com.dev.cinema.model.dto.order.OrderResponseDto;
import com.dev.cinema.service.OrderService;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/order")
public class OrderController {
    @Autowired
    private OrderService orderService;
    @Autowired
    private OrderMapper orderMapper;

    @PostMapping
    public Order add(@RequestBody OrderRequestDto dto) {
        return orderService.completeOrder(orderMapper.getOrderFromRequest(dto));
    }

    @GetMapping("/user/{userId}")
    public List<OrderResponseDto> getAll(@PathVariable("userId") Long userId) {
        return orderService.getOrderHistory(userId)
                .stream()
                .map(orderMapper::getOrderResponce)
                .collect(Collectors.toList());
    }
}
