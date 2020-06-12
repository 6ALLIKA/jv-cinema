package com.dev.cinema.controller;

import com.dev.cinema.model.dto.mapperdto.ShoppingCartMapper;
import com.dev.cinema.model.dto.shoppingcart.ShoppingCartResponseDto;
import com.dev.cinema.service.MovieSessionService;
import com.dev.cinema.service.ShoppingCartService;
import com.dev.cinema.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/shopping-cart")
public class ShoppingCartController {
    @Autowired
    private ShoppingCartService shoppingCartService;
    @Autowired
    private UserService userService;
    @Autowired
    private MovieSessionService movieSessionService;
    @Autowired
    private ShoppingCartMapper shoppingCartMapper;

    @PostMapping("/add-moviesession")
    public void add(@RequestParam("sessionId") Long sessinId,
                    @RequestParam("userId") Long userId) {
        shoppingCartService
                .addSession(movieSessionService.getById(sessinId), userService.getById(userId));
    }

    @GetMapping("/by-user")
    public ShoppingCartResponseDto getByUser(@RequestParam("userId") Long userId) {
        return shoppingCartMapper.getShoppingCartResponse(shoppingCartService.getByUserId(userId));
    }
}
