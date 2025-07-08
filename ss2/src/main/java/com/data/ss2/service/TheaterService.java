package com.data.ss2.service;

import com.data.ss2.entity.Theater;

import java.util.List;

public interface TheaterService {
    List<Theater> findByNameContainingIgnoreCase(String name);
    List<Theater> findByAddressContainingIgnoreCase(String address);
    Theater save(Theater theater);
    Theater findById(Long id);
    List<Theater> findAll();
    void deleteById(Long id);
}
