package com.dev.cinema.controller;

import com.dev.cinema.model.User;
import com.dev.cinema.model.dto.mapperdto.UserMapper;
import com.dev.cinema.model.dto.user.UserResponseDto;
import com.dev.cinema.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private UserMapper userMapper;

    @GetMapping("/by-email/{email}/")
    public UserResponseDto getUserByEmail(@PathVariable("email") String email) {
        User user = userService.findByEmail(email).orElseThrow(() -> new RuntimeException("Not valid email"));
        return userMapper.getUserResponse(user);
    }
}
