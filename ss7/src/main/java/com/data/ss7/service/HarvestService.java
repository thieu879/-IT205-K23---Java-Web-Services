package com.data.ss7.service;

import com.data.ss7.model.Harvest;

import java.util.List;

public interface HarvestService {
    List<Harvest> getAllHarvests();
    Harvest getHarvestById(Long id);
    Harvest addHarvest(Harvest harvest);
}