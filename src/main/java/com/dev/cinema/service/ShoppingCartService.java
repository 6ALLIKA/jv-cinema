package com.dev.cinema.service;

import com.dev.cinema.model.MovieSession;
import com.dev.cinema.model.ShoppingCart;
import com.dev.cinema.model.User;

public interface ShoppingCartService {

    void addSession(MovieSession movieSession, User user);

    ShoppingCart getByUserId(Long userId);

    void registerNewShoppingCart(User user);

    void clear(ShoppingCart shoppingCart);

    ShoppingCart getById(Long id);
}
