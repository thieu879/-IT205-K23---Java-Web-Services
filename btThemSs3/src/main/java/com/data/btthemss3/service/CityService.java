package com.data.btthemss3.service;

import com.data.btthemss3.entity.City;

import java.util.List;

public interface CityService {
    List<City> findAll();
    City findById(Integer id);
    City save(City city);
    boolean deleteById(Integer id);
    City update(City city);
    List<City> findByName(String name);
}
