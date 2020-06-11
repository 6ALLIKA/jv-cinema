package com.dev.cinema.controller;

import com.dev.cinema.model.Movie;
import com.dev.cinema.model.dto.mapperdto.MovieMapper;
import com.dev.cinema.model.dto.movie.MovieRequestDto;
import com.dev.cinema.model.dto.movie.MovieResponseDto;
import com.dev.cinema.service.MovieService;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/movie")
public class MovieController {
    @Autowired
    private MovieService movieService;
    @Autowired
    private MovieMapper movieMapper;

    @PostMapping
    public Movie add(@RequestBody MovieRequestDto dto) {
        return movieService.add(movieMapper.getMovieFromRequest(dto));
    }

    @GetMapping
    public List<MovieResponseDto> getAll() {
        return movieService.getAll()
                .stream()
                .map(movieMapper::getMovieResponce)
                .collect(Collectors.toList());
    }
}
