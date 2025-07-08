package com.data.ss2.service;

import com.data.ss2.entity.Seat;
import com.data.ss2.repository.SeatRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class SeatServiceImpl implements SeatService {
    private SeatRepository seatRepository;
    public SeatServiceImpl(SeatRepository seatRepository) {
        this.seatRepository = seatRepository;
    }
    @Override
    public List<Seat> findBySeatNumber(String seatNumber) {
        return seatRepository.findBySeatNumber(seatNumber);
    }

    @Override
    public List<Seat> findByScreenRoomId(Long screenRoomId) {
        return seatRepository.findByScreenRoomId(screenRoomId);
    }

    @Override
    public Seat save(Seat seat) {
        return seatRepository.save(seat);
    }

    @Override
    public Seat findById(Long id) {
        return seatRepository.findById(id);
    }

    @Override
    public List<Seat> findAll() {
        return seatRepository.findAll();
    }

    @Override
    public void deleteById(Long id) {
        seatRepository.deleteById(id);
    }
}
