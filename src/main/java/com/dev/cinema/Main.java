package com.dev.cinema;

import com.dev.cinema.library.Injector;
import com.dev.cinema.model.CinemaHall;
import com.dev.cinema.model.Movie;
import com.dev.cinema.model.MovieSession;
import com.dev.cinema.service.CinemaHallService;
import com.dev.cinema.service.MovieService;
import com.dev.cinema.service.MovieSessionService;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

public class Main {
    private static Injector injector = Injector.getInstance("com.dev.cinema");

    public static void main(String[] args) {
        Movie movie = new Movie();
        movie.setTitle("Fast and Furious");
        MovieService movieService = (MovieService) injector.getInstance(MovieService.class);
        movieService.add(movie);
        List<Movie> movieList = movieService.getAll();
        movieList.forEach(System.out::println);

        CinemaHall hall = new CinemaHall();
        hall.setCapacity(150);
        hall.setDescription("Green");
        CinemaHallService cinemaHallService =
                (CinemaHallService) injector.getInstance(CinemaHallService.class);
        cinemaHallService.add(hall);
        List<CinemaHall> cinemaHalls = cinemaHallService.getAll();
        cinemaHalls.forEach(System.out::println);

        MovieSession session = new MovieSession();
        session.setCinemaHall(hall);
        session.setMovie(movie);
        session.setShowTime(LocalDateTime.of(LocalDate.of(2020, 5, 26),
                LocalTime.of(12, 30)));
        MovieSessionService movieSessionService =
                (MovieSessionService) injector.getInstance(MovieSessionService.class);
        movieSessionService.add(session);

        LocalDate dateBefore = LocalDate.of(2020, 5, 20);
        LocalDate dateAfter = LocalDate.of(2020, 5, 22);

        List<MovieSession> availableSessions1 =
                movieSessionService.findAvailableSessions(1L, dateBefore);
        System.out.println("day before");
        availableSessions1.forEach(System.out::println);

        List<MovieSession> availableSessions2 =
                movieSessionService.findAvailableSessions(1L, dateAfter);
        System.out.println("day after");
        availableSessions2.forEach(System.out::println);
    }
}
