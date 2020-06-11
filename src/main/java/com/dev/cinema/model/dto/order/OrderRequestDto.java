package com.dev.cinema.model.dto.order;

import lombok.Data;

@Data
public class OrderRequestDto {
    private String orderTime;
    private Long userId;
}
