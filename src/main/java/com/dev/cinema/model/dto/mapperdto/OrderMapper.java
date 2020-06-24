package com.dev.cinema.model.dto.mapperdto;

import com.dev.cinema.model.Order;
import com.dev.cinema.model.Ticket;
import com.dev.cinema.model.dto.order.OrderResponseDto;
import java.util.stream.Collectors;
import org.springframework.stereotype.Component;

@Component
public class OrderMapper {

    public OrderResponseDto getOrderResponse(Order order) {
        OrderResponseDto dto = new OrderResponseDto();
        dto.setOrderDate(order.getTime());
        dto.setUserId(order.getUser().getId());
        dto.setTicketIds(order.getTickets()
                .stream()
                .map(Ticket::getId)
                .collect(Collectors.toList()));
        return dto;
    }
}
