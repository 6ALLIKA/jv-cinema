package com.dev.cinema.controller;

import com.dev.cinema.model.Movie;
import com.dev.cinema.model.dto.mapperdto.MovieMapper;
import com.dev.cinema.model.dto.movie.MovieRequestDto;
import com.dev.cinema.model.dto.movie.MovieResponseDto;
import com.dev.cinema.service.MovieService;
import java.util.List;
import java.util.stream.Collectors;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/movie")
public class MovieController {
    private final MovieService movieService;
    private final MovieMapper movieMapper;

    @Autowired
    public MovieController(MovieService movieService, MovieMapper movieMapper) {
        this.movieService = movieService;
        this.movieMapper = movieMapper;
    }

    @PostMapping
    public Movie add(@RequestBody @Valid MovieRequestDto dto) {
        return movieService.add(movieMapper.getMovieFromRequest(dto));
    }

    @GetMapping
    public List<MovieResponseDto> getAll() {
        return movieService.getAll()
                .stream()
                .map(movieMapper::getMovieResponse)
                .collect(Collectors.toList());
    }
}
