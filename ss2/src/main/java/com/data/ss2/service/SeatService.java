package com.data.ss2.service;

import com.data.ss2.entity.Seat;

import java.util.List;

public interface SeatService {
    List<Seat> findBySeatNumber(String seatNumber);
    List<Seat> findByScreenRoomId(Long screenRoomId);
    Seat save(Seat seat);
    Seat findById(Long id);
    List<Seat> findAll();
    void deleteById(Long id);
}
