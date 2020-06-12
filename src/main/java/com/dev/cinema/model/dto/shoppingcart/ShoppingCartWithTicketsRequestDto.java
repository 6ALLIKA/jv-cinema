package com.dev.cinema.model.dto.shoppingcart;

import java.util.List;
import lombok.Data;

@Data
public class ShoppingCartWithTicketsRequestDto {
    private List<Long> ticketIds;
    private Long userId;
}
