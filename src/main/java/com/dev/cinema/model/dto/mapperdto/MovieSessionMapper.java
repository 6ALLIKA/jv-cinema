package com.dev.cinema.model.dto.mapperdto;

import com.dev.cinema.model.MovieSession;
import com.dev.cinema.model.dto.moviesession.MovieSessionRequestDto;
import com.dev.cinema.model.dto.moviesession.MovieSessionResponseDto;
import com.dev.cinema.service.CinemaHallService;
import com.dev.cinema.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MovieSessionMapper {
    private final CinemaHallService cinemaHallService;
    private final MovieService movieService;

    @Autowired
    public MovieSessionMapper(CinemaHallService cinemaHallService, MovieService movieService) {
        this.cinemaHallService = cinemaHallService;
        this.movieService = movieService;
    }

    public MovieSessionResponseDto getMovieSessionResponse(MovieSession movieSession) {
        MovieSessionResponseDto dto = new MovieSessionResponseDto();
        dto.setId(movieSession.getId());
        dto.setMovieId(movieSession.getMovie().getId());
        dto.setMovieTitle(movieSession.getMovie().getTitle());
        dto.setCinemaHallId(movieSession.getCinemaHall().getId());
        dto.setShowTime(movieSession.getShowTime());
        return dto;
    }

    public MovieSession getMovieSessionFromRequest(MovieSessionRequestDto dto) {
        MovieSession movieSession = new MovieSession();
        movieSession.setShowTime(dto.getShowTime());
        movieSession.setMovie(movieService.getById(dto.getMovieId()));
        movieSession.setCinemaHall(cinemaHallService.getById(dto.getCinemaHallId()));
        return movieSession;
    }
}
