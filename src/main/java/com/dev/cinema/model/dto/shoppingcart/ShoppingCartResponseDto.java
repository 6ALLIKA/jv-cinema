package com.dev.cinema.model.dto.shoppingcart;

import java.util.List;
import lombok.Data;

@Data
public class ShoppingCartResponseDto {
    private Long userId;
    private List<Long> ticketIds;
}
