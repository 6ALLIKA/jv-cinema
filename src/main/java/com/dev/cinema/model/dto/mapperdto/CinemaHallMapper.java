package com.dev.cinema.model.dto.mapperdto;

import com.dev.cinema.model.CinemaHall;
import com.dev.cinema.model.dto.cinemahall.CinemaHallRequestDto;
import com.dev.cinema.model.dto.cinemahall.CinemaHallResponseDto;
import org.springframework.stereotype.Component;

@Component
public class CinemaHallMapper {

    public CinemaHallResponseDto getCinemaHallResponce(CinemaHall cinemaHall) {
        CinemaHallResponseDto dto = new CinemaHallResponseDto();
        dto.setCapacity(cinemaHall.getCapacity());
        dto.setId(cinemaHall.getId());
        return dto;
    }

    public CinemaHall getCinemaHallFromRequest(CinemaHallRequestDto dto) {
        CinemaHall cinemaHall = new CinemaHall();
        cinemaHall.setCapacity(dto.getCapacity());
        cinemaHall.setDescription(dto.getDescription());
        return cinemaHall;
    }
}
