package com.dev.cinema.controller;

import com.dev.cinema.model.MovieSession;
import com.dev.cinema.model.User;
import com.dev.cinema.model.dto.mapperdto.ShoppingCartMapper;
import com.dev.cinema.model.dto.shoppingcart.ShoppingCartResponseDto;
import com.dev.cinema.model.dto.shoppingcart.ShoppingCartWithSessionRequestDto;
import com.dev.cinema.service.ShoppingCartService;
import com.dev.cinema.service.UserService;
import java.util.ArrayList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/shopping-cart")
public class ShoppingCartController {
    @Autowired
    ShoppingCartService shoppingCartService;
    @Autowired
    UserService userService;
    @Autowired
    ShoppingCartMapper shoppingCartMapper;

    @PostMapping("/add-moviesession")
    public void add(@RequestBody ShoppingCartWithSessionRequestDto dto,
                            @PathVariable("userId") Long id) {
        ArrayList<Object> array = shoppingCartMapper.getMovieSessionInShoppingCartFromRequest(dto);
        shoppingCartService.addSession((MovieSession) array.get(0), (User) array.get(1));
    }

    @GetMapping("/by-user")
    public ShoppingCartResponseDto getByUser(@PathVariable("userId") Long userId) {
        return shoppingCartMapper.getShoppingCartResponce(shoppingCartService.getByUserId(userId));
    }
}
