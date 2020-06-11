package com.dev.cinema.model.dto.shoppingcart;

import lombok.Data;

@Data
public class ShoppingCartWithSessionRequestDto {
    private Long movieSessionId;
    private Long userId;
}
