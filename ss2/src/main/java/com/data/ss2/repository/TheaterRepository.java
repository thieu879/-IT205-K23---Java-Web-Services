package com.data.ss2.repository;

import com.data.ss2.entity.Theater;
import java.util.List;

public interface TheaterRepository {
    List<Theater> findByNameContainingIgnoreCase(String name);
    List<Theater> findByAddressContainingIgnoreCase(String address);
    Theater save(Theater theater);
    Theater findById(Long id);
    List<Theater> findAll();
    void deleteById(Long id);
}
