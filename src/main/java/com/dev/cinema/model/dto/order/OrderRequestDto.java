package com.dev.cinema.model.dto.order;

import java.time.LocalDateTime;
import lombok.Data;

@Data
public class OrderRequestDto {
    private LocalDateTime orderTime;
    private Long userId;
}
