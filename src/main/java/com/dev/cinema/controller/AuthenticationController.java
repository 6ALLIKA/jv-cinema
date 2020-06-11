package com.dev.cinema.controller;

import com.dev.cinema.model.dto.mapperdto.UserMapper;
import com.dev.cinema.model.User;
import com.dev.cinema.model.dto.user.UserRequestDto;
import com.dev.cinema.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/authentication")
public class AuthenticationController {
    @Autowired
    UserService userService;
    @Autowired
    UserMapper userMapper;

    @PostMapping("/register")
    public User register(@RequestBody UserRequestDto dto) {
        return userService.add(userMapper.getUserFromRequest(dto));
    }
}
