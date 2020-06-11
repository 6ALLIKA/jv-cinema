package com.dev.cinema.model.dto.order;

import java.util.List;
import lombok.Data;

@Data
public class OrderResponseDto {
    private Long id;
    private List<Long> ticketIds;
    private Long userId;
    private String orderDate;
}
