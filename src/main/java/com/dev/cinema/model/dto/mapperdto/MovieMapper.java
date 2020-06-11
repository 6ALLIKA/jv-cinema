package com.dev.cinema.model.dto.mapperdto;

import com.dev.cinema.model.Movie;
import com.dev.cinema.model.dto.movie.MovieDetailedResponseDto;
import com.dev.cinema.model.dto.movie.MovieRequestDto;
import com.dev.cinema.model.dto.movie.MovieResponseDto;
import org.springframework.stereotype.Component;

@Component
public class MovieMapper {

    public MovieResponseDto getMovieResponce(Movie movie) {
        MovieResponseDto dto = new MovieResponseDto();
        dto.setId(movie.getId());
        dto.setTitle(movie.getTitle());
        return dto;
    }

    public MovieDetailedResponseDto getMovieResponceDetailed(Movie movie) {
        MovieDetailedResponseDto dto = new MovieDetailedResponseDto();
        dto.setTitle(movie.getTitle());
        dto.setDescription(movie.getDescription());
        return dto;
    }

    public Movie getMovieFromRequest(MovieRequestDto dto) {
        Movie movie = new Movie();
        movie.setDescription(dto.getDescription());
        movie.setTitle(dto.getTitle());
        return movie;
    }
}
