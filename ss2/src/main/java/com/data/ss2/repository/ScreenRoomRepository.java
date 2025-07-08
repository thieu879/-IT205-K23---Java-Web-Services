package com.data.ss2.repository;

import com.data.ss2.entity.ScreenRoom;
import java.util.List;

public interface ScreenRoomRepository {
    List<ScreenRoom> findByNameContainingIgnoreCase(String name);
    List<ScreenRoom> findByTheaterId(Long theaterId);
    ScreenRoom save(ScreenRoom screenRoom);
    ScreenRoom findById(Long id);
    List<ScreenRoom> findAll();
    void deleteById(Long id);
}
