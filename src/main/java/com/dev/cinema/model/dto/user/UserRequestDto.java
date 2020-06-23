package com.dev.cinema.model.dto.user;

import com.dev.cinema.security.validation.EmailConstraint;
import com.dev.cinema.security.validation.MatchingPasswordsConstraint;
import javax.validation.constraints.NotNull;
import lombok.Data;

@Data
@MatchingPasswordsConstraint
public class UserRequestDto {
    @NotNull
    private String name;
    @NotNull
    @EmailConstraint
    private String email;
    @NotNull
    private String password;
    @NotNull
    private String repeatPassword;
}
