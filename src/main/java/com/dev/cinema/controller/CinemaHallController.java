package com.dev.cinema.controller;

import com.dev.cinema.model.CinemaHall;
import com.dev.cinema.model.dto.cinemahall.CinemaHallRequestDto;
import com.dev.cinema.model.dto.cinemahall.CinemaHallResponseDto;
import com.dev.cinema.model.dto.mapperdto.CinemaHallMapper;
import com.dev.cinema.service.CinemaHallService;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/cinema-hall")
public class CinemaHallController {
    @Autowired
    CinemaHallService cinemaHallService;
    @Autowired
    CinemaHallMapper cinemaHallMapper;

    @PostMapping("/add")
    public CinemaHall add(@RequestBody CinemaHallRequestDto dto) {
        return cinemaHallService.add(cinemaHallMapper.getCinemaHallFromRequest(dto));
    }

    @GetMapping
    public List<CinemaHallResponseDto> getAll() {
        return cinemaHallService.getAll()
                .stream()
                .map(cinemaHallMapper::getCinemaHallResponce)
                .collect(Collectors.toList());
    }

}
