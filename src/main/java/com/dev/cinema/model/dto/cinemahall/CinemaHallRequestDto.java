package com.dev.cinema.model.dto.cinemahall;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import lombok.Data;

@Data
public class CinemaHallRequestDto {
    @NotNull
    @Min(30)
    private Integer capacity;
    @NotNull
    private String description;
}
