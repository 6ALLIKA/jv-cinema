package com.dev.cinema.model.dto.moviesession;

import java.time.LocalDateTime;
import lombok.Data;

@Data
public class MovieSessionResponseDto {
    private Long id;
    private Long movieId;
    private String movieTitle;
    private Long cinemaHallId;
    private LocalDateTime showTime;
}
