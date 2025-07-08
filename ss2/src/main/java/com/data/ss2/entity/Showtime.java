package com.data.ss2.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
public class Showtime {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "movie_id", nullable = false)
    private Movie movie;
    @ManyToOne
    @JoinColumn(name = "screen_room_id", nullable = false)
    private ScreenRoom screenRoom;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private int numberSeatEmpty;

    public Showtime() {
    }
    public Showtime(Movie movie, ScreenRoom screenRoom, LocalDateTime startTime, LocalDateTime endTime, int numberSeatEmpty) {
        this.movie = movie;
        this.screenRoom = screenRoom;
        this.startTime = startTime;
        this.endTime = endTime;
        this.numberSeatEmpty = numberSeatEmpty;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Movie getMovie() {
        return movie;
    }

    public void setMovie(Movie movie) {
        this.movie = movie;
    }

    public ScreenRoom getScreenRoom() {
        return screenRoom;
    }

    public void setScreenRoom(ScreenRoom screenRoom) {
        this.screenRoom = screenRoom;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }

    public LocalDateTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalDateTime endTime) {
        this.endTime = endTime;
    }

    public int getNumberSeatEmpty() {
        return numberSeatEmpty;
    }

    public void setNumberSeatEmpty(int numberSeatEmpty) {
        this.numberSeatEmpty = numberSeatEmpty;
    }
}
