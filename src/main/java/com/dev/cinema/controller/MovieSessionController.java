package com.dev.cinema.controller;

import com.dev.cinema.model.MovieSession;
import com.dev.cinema.model.dto.mapperdto.MovieSessionMapper;
import com.dev.cinema.model.dto.moviesession.MovieSessionRequestDto;
import com.dev.cinema.model.dto.moviesession.MovieSessionResponseDto;
import com.dev.cinema.service.MovieSessionService;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/movie-session")
public class MovieSessionController {
    @Autowired
    MovieSessionService movieSessionService;
    @Autowired
    MovieSessionMapper movieSessionMapper;

    @PostMapping("/add")
    public MovieSession add(@RequestBody MovieSessionRequestDto dto) {
        return movieSessionService.add(movieSessionMapper.getMovieSessionFromRequest(dto));
    }

    @GetMapping("/available{movieId}{date}")
    public List<MovieSessionResponseDto>
            getAvailableMovieSession(@PathVariable("id") Long movieId,
                                @PathVariable("date") LocalDate date) {
        return movieSessionService
                .findAvailableSessions(movieId, date)
                .stream()
                .map(movieSessionMapper::getMovieSessionResponce)
                .collect(Collectors.toList());
    }
}
