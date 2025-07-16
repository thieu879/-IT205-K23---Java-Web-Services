package com.data.ss7.service;

import com.data.ss7.model.Seed;

import java.util.List;

public interface SeedService {
    List<Seed> getAllSeeds();
    Seed getSeedById(Long id);
    Seed addSeed(Seed seed);
    Seed updateSeed(Long id, Seed seed);
    void deleteSeed(Long id);
}
