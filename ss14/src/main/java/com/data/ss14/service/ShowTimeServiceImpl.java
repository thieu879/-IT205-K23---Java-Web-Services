package com.data.ss14.service;

import com.data.ss14.model.dto.request.ShowTimeRequestDTO;
import com.data.ss14.model.entity.Movie;
import com.data.ss14.model.entity.ShowTime;
import com.data.ss14.repository.MovieRepo;
import com.data.ss14.repository.ShowTimeRepo;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ShowTimeServiceImpl implements ShowTimeService{
    private final ShowTimeRepo showTimeRepo;
    private final MovieRepo movieRepo;

    @Override
    public List<ShowTime> getAllShowTimes(){
        return showTimeRepo.findAll();
    }

    @Override
    public ShowTime saveShowTime(ShowTimeRequestDTO showTimeRequestDTO){
        Movie movie = movieRepo.findById(showTimeRequestDTO.getMovieId())
                .orElseThrow(() -> new EntityNotFoundException("movie with id " + showTimeRequestDTO.getMovieId() + " not found"));
        if(showTimeRepo.existsByMovieId(showTimeRequestDTO.getMovieId())){
            throw new IllegalArgumentException("Movie is already shown");
        }
        return showTimeRepo.save(ShowTime.builder()
                .movie(movie)
                .startTime(LocalDateTime.parse(showTimeRequestDTO.getStartTime()))
                .room(showTimeRequestDTO.getRoom())
                .build());
    }
}