package com.data.btthemss3.service;

import com.data.btthemss3.entity.City;
import com.data.btthemss3.repository.CityRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class CityServiceImpl implements CityService {
    private CityRepository cityRepository;

    public CityServiceImpl(CityRepository cityRepository) {
        this.cityRepository = cityRepository;
    }

    @Override
    public List<City> findAll() {
        return cityRepository.findAll();
    }

    @Override
    public City findById(Integer id) {
        try {
            return cityRepository.findById(id).orElse(null);
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public City save(City city) {
        try {
            return cityRepository.save(city);
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public boolean deleteById(Integer id) {
        try {
            cityRepository.deleteById(id);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public City update(City city) {
        try {
            return cityRepository.save(city);
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public List<City> findByName(String name) {
        return cityRepository.findByCityNameContainingIgnoreCase(name);
    }
}
