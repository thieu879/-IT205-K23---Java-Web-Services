package com.data.btthemss3.service;

import com.data.btthemss3.entity.Country;

import java.util.List;

public interface CountryService {
    List<Country> findAll();
    Country findById(Integer id);
    Country save(Country country);
    boolean deleteById(Integer id);
    Country update(Country country);
    List<Country> findByName(String name);
}
