package com.dev.cinema.model.dto.mapperdto;

import com.dev.cinema.model.MovieSession;
import com.dev.cinema.model.ShoppingCart;
import com.dev.cinema.model.Ticket;
import com.dev.cinema.model.User;
import com.dev.cinema.model.dto.shoppingcart.ShoppingCartResponseDto;
import com.dev.cinema.model.dto.shoppingcart.ShoppingCartWithSessionRequestDto;
import com.dev.cinema.model.dto.shoppingcart.ShoppingCartWithTicketsRequestDto;
import com.dev.cinema.service.MovieSessionService;
import com.dev.cinema.service.ShoppingCartService;
import com.dev.cinema.service.UserService;
import java.util.ArrayList;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ShoppingCartMapper {
    @Autowired
    private UserService userService;
    @Autowired
    private MovieSessionService movieSessionService;
    @Autowired
    private ShoppingCartService shoppingCartService;

    public ShoppingCartResponseDto getShoppingCartResponce (ShoppingCart shoppingCart) {
        ShoppingCartResponseDto dto = new ShoppingCartResponseDto();
        dto.setUserId(shoppingCart.getUser().getId());
        dto.setTicketIds(shoppingCart.getTickets()
                .stream()
                .map(Ticket::getId)
                .collect(Collectors.toList()));
        return dto;
    }

    public ShoppingCart getShoppingCartWithTicketsFromRequest (ShoppingCartWithTicketsRequestDto dto) {
        ShoppingCart shoppingCart = new ShoppingCart();
        shoppingCart.setUser(userService.getById(dto.getUserId()));
        shoppingCart.setTickets(shoppingCartService
                .getByUserId(shoppingCart.getUser())
                .getTickets());
        return shoppingCart;
    }

    public ArrayList<Object> getMovieSessionInShoppingCartFromRequest (ShoppingCartWithSessionRequestDto dto) {
        MovieSession movieSession = movieSessionService.getById(dto.getMovieSessionId());
        User user = userService.getById(dto.getUserId());
        ArrayList<Object> arrayList = new ArrayList<>();
        arrayList.add(movieSession);
        arrayList.add(user);
        return arrayList;
    }
}
