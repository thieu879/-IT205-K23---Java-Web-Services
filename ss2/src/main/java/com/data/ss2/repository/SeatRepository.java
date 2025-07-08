package com.data.ss2.repository;

import com.data.ss2.entity.Seat;
import java.util.List;

public interface SeatRepository {
    List<Seat> findBySeatNumber(String seatNumber);
    List<Seat> findByScreenRoomId(Long screenRoomId);
    Seat save(Seat seat);
    Seat findById(Long id);
    List<Seat> findAll();
    void deleteById(Long id);
}
