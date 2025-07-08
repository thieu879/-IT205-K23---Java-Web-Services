package com.data.ss2.service;

import com.data.ss2.entity.ScreenRoom;
import com.data.ss2.repository.ScreenRoomRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class ScreenRoomServiceImpl implements ScreenRoomService {
    private ScreenRoomRepository screenRoomRepository;
    public ScreenRoomServiceImpl(ScreenRoomRepository screenRoomRepository) {
        this.screenRoomRepository = screenRoomRepository;
    }
    @Override
    public List<ScreenRoom> findByNameContainingIgnoreCase(String name) {
        return screenRoomRepository.findByNameContainingIgnoreCase(name);
    }

    @Override
    public List<ScreenRoom> findByTheaterId(Long theaterId) {
        return screenRoomRepository.findByTheaterId(theaterId);
    }

    @Override
    public ScreenRoom save(ScreenRoom screenRoom) {
        return screenRoomRepository.save(screenRoom);
    }

    @Override
    public ScreenRoom findById(Long id) {
        return screenRoomRepository.findById(id);
    }

    @Override
    public List<ScreenRoom> findAll() {
        return screenRoomRepository.findAll();
    }

    @Override
    public void deleteById(Long id) {
        screenRoomRepository.deleteById(id);
    }
}
