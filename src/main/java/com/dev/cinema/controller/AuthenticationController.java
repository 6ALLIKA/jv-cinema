package com.dev.cinema.controller;

import com.dev.cinema.model.dto.mapperdto.UserMapper;
import com.dev.cinema.model.dto.user.UserRequestDto;
import com.dev.cinema.model.dto.user.UserResponseDto;
import com.dev.cinema.security.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/authentication")
public class AuthenticationController {
    @Autowired
    private AuthenticationService authenticationService;
    @Autowired
    private UserMapper userMapper;

    @PostMapping("/register")
    public UserResponseDto register(@RequestBody UserRequestDto dto) {
        return userMapper.getUserResponce(authenticationService
                        .register(userMapper
                                .getUserFromRequest(dto)));
    }
}
