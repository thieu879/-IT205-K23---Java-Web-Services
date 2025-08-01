package com.data.ss2.service;

import com.data.ss2.entity.Showtime;

import java.time.LocalDate;
import java.util.List;

public interface ShowtimeService {
    List<Showtime> findByMovieId(Long movieId);
    List<Showtime> findByScreenRoomId(Long screenRoomId);
    List<Showtime> filterShowtimes(Long movieId, Long screenRoomId, LocalDate date);
    Showtime save(Showtime showtime);
    Showtime findById(Long id);
    List<Showtime> findAll();
    void deleteById(Long id);
}
