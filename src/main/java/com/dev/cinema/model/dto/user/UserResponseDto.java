package com.dev.cinema.model.dto.user;

import java.util.Set;
import lombok.Data;

@Data
public class UserResponseDto {
    private Long id;
    private String name;
    private String email;
    private Set<String> roles;
}
