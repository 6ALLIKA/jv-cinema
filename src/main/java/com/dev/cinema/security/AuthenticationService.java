package com.dev.cinema.security;

import com.dev.cinema.exception.AuthenticationException;
import com.dev.cinema.model.User;

public interface AuthenticationService {
    User login(String login, String password) throws AuthenticationException;

    User register(User user);
}
