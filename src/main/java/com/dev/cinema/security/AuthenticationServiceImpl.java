package com.dev.cinema.security;

import com.dev.cinema.exception.AuthenticationException;
import com.dev.cinema.model.User;
import com.dev.cinema.service.ShoppingCartService;
import com.dev.cinema.service.UserService;
import com.dev.cinema.util.HashUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationServiceImpl implements AuthenticationService {
    private final UserService userService;
    private final ShoppingCartService shoppingCartService;
    private final HashUtil hashUtil;

    @Autowired
    public AuthenticationServiceImpl(UserService userService,
                                     ShoppingCartService shoppingCartService, HashUtil hashUtil) {
        this.userService = userService;
        this.shoppingCartService = shoppingCartService;
        this.hashUtil = hashUtil;
    }

    @Override
    public User login(String email, String password) throws AuthenticationException {
        User user = userService.findByEmail(email);
        if (user == null || !hashUtil.hashPassword(password, user.getSalt())
                .equals(user.getPassword())) {
            throw new AuthenticationException("The login or password is incorrect");
        }
        return user;
    }

    @Override
    public User register(User user) {
        byte[] salt = hashUtil.getSalt();
        user.setSalt(salt);
        user.setPassword(hashUtil.hashPassword(user.getPassword(), salt));
        userService.add(user);
        shoppingCartService.registerNewShoppingCart(user);
        return user;
    }
}
