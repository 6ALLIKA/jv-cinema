package com.dev.cinema.controller;

import com.dev.cinema.model.User;
import com.dev.cinema.model.dto.mapperdto.ShoppingCartMapper;
import com.dev.cinema.model.dto.shoppingcart.ShoppingCartResponseDto;
import com.dev.cinema.service.MovieSessionService;
import com.dev.cinema.service.ShoppingCartService;
import com.dev.cinema.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/shopping-cart")
public class ShoppingCartController {
    private final ShoppingCartService shoppingCartService;
    private final UserService userService;
    private final MovieSessionService movieSessionService;
    private final ShoppingCartMapper shoppingCartMapper;

    @Autowired
    public ShoppingCartController(ShoppingCartService shoppingCartService,
                                  UserService userService,
                                  MovieSessionService movieSessionService,
                                  ShoppingCartMapper shoppingCartMapper) {
        this.shoppingCartService = shoppingCartService;
        this.userService = userService;
        this.movieSessionService = movieSessionService;
        this.shoppingCartMapper = shoppingCartMapper;
    }

    @PostMapping("/add-moviesession")
    public void add(@RequestParam("sessionId") Long sessinId,
                    Authentication authentication) {
        User user = userService.findByEmail(authentication.getName());
        shoppingCartService
                .addSession(movieSessionService.getById(sessinId), user);
    }

    @GetMapping
    public ShoppingCartResponseDto getByUser(Authentication authentication) {
        User user = userService.findByEmail(authentication.getName());
        return shoppingCartMapper.getShoppingCartResponse(shoppingCartService.getByUser(user));
    }
}
