package com.data.ss2.service;

import com.data.ss2.entity.Theater;
import com.data.ss2.repository.TheaterRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class TheaterServiceImpl implements TheaterService {
    private TheaterRepository theaterRepository;
    public TheaterServiceImpl(TheaterRepository theaterRepository) {
        this.theaterRepository = theaterRepository;
    }
    @Override
    public List<Theater> findByNameContainingIgnoreCase(String name) {
        return theaterRepository.findByNameContainingIgnoreCase(name);
    }

    @Override
    public List<Theater> findByAddressContainingIgnoreCase(String address) {
        return theaterRepository.findByAddressContainingIgnoreCase(address);
    }

    @Override
    public Theater save(Theater theater) {
        return theaterRepository.save(theater);
    }

    @Override
    public Theater findById(Long id) {
        return theaterRepository.findById(id);
    }

    @Override
    public List<Theater> findAll() {
        return theaterRepository.findAll();
    }

    @Override
    public void deleteById(Long id) {
        theaterRepository.deleteById(id);
    }
}
