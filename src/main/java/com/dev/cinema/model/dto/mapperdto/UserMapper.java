package com.dev.cinema.model.dto.mapperdto;

import com.dev.cinema.model.User;
import com.dev.cinema.model.dto.user.UserRequestDto;
import com.dev.cinema.model.dto.user.UserResponseDto;
import java.util.stream.Collectors;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    public UserResponseDto getUserResponse(User user) {
        UserResponseDto dto = new UserResponseDto();
        dto.setEmail(user.getEmail());
        dto.setName(user.getName());
        dto.setRoles(user.getRoles()
                .stream()
                .map(role -> role.getName().name())
                .collect(Collectors.toSet()));
        dto.setId(user.getId());
        return dto;
    }

    public User getUserFromRequest(UserRequestDto dto) {
        User user = new User();
        user.setEmail(dto.getEmail());
        user.setName(dto.getName());
        user.setPassword(dto.getPassword());
        return user;
    }
}
