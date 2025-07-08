package com.data.ss2.service;

import com.data.ss2.entity.Showtime;
import com.data.ss2.repository.ShowtimeRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Service
@Transactional
public class ShowtimeServiceImpl implements ShowtimeService{
    private ShowtimeRepository showtimeRepository;
    public ShowtimeServiceImpl(ShowtimeRepository showtimeRepository) {
        this.showtimeRepository = showtimeRepository;
    }
    @Override
    public List<Showtime> findByMovieId(Long movieId) {
        return showtimeRepository.findByMovieId(movieId);
    }

    @Override
    public List<Showtime> findByScreenRoomId(Long screenRoomId) {
        return showtimeRepository.findByScreenRoomId(screenRoomId);
    }

    @Override
    public List<Showtime> filterShowtimes(Long movieId, Long screenRoomId, LocalDate date) {
        return showtimeRepository.filterShowtimes(movieId, screenRoomId, date);
    }

    @Override
    public Showtime save(Showtime showtime) {
        return showtimeRepository.save(showtime);
    }

    @Override
    public Showtime findById(Long id) {
        return showtimeRepository.findById(id);
    }

    @Override
    public List<Showtime> findAll() {
        return showtimeRepository.findAll();
    }

    @Override
    public void deleteById(Long id) {
        showtimeRepository.deleteById(id);
    }
}
