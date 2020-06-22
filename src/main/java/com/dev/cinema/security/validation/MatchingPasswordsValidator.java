package com.dev.cinema.security.validation;

import com.dev.cinema.model.dto.user.UserRequestDto;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class MatchingPasswordsValidator
        implements ConstraintValidator<MatchingPasswordsConstraint, UserRequestDto> {
    private static final int MIN_LENGTH = 4;
    private static final int MAX_LENGTH = 30;

    @Override
    public boolean isValid(UserRequestDto userRequestDto, ConstraintValidatorContext context) {
        String passwordValue = userRequestDto.getPassword();
        String repeatedPasswordValue = userRequestDto.getRepeatPassword();
        return passwordValue != null && passwordValue.equals(repeatedPasswordValue)
                && passwordValue.length() >= MIN_LENGTH && passwordValue.length() <= MAX_LENGTH;
    }
}
