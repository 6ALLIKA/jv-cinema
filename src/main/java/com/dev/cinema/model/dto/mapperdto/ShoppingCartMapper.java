package com.dev.cinema.model.dto.mapperdto;

import com.dev.cinema.model.ShoppingCart;
import com.dev.cinema.model.Ticket;
import com.dev.cinema.model.dto.shoppingcart.ShoppingCartResponseDto;
import com.dev.cinema.model.dto.shoppingcart.ShoppingCartWithTicketsRequestDto;
import com.dev.cinema.service.ShoppingCartService;
import com.dev.cinema.service.UserService;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ShoppingCartMapper {
    @Autowired
    private UserService userService;
    @Autowired
    private ShoppingCartService shoppingCartService;

    public ShoppingCartResponseDto getShoppingCartResponse(ShoppingCart shoppingCart) {
        ShoppingCartResponseDto dto = new ShoppingCartResponseDto();
        dto.setUserId(shoppingCart.getUser().getId());
        dto.setTicketIds(shoppingCart.getTickets()
                .stream()
                .map(Ticket::getId)
                .collect(Collectors.toList()));
        return dto;
    }

    public ShoppingCart
            getShoppingCartWithTicketsFromRequest(ShoppingCartWithTicketsRequestDto dto) {

        ShoppingCart shoppingCart = new ShoppingCart();
        shoppingCart.setUser(userService.getById(dto.getUserId()));
        shoppingCart.setTickets(shoppingCartService
                .getByUserId(shoppingCart.getUser().getId())
                .getTickets());
        return shoppingCart;
    }
}
