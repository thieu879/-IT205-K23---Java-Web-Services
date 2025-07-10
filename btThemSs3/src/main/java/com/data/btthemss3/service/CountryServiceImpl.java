package com.data.btthemss3.service;

import com.data.btthemss3.entity.Country;
import com.data.btthemss3.repository.CountryRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class CountryServiceImpl implements CountryService {
    private CountryRepository countryRepository;

    public CountryServiceImpl(CountryRepository countryRepository) {
        this.countryRepository = countryRepository;
    }

    @Override
    public List<Country> findAll() {
        return countryRepository.findAll();
    }

    @Override
    public Country findById(Integer id) {
        try {
            return countryRepository.findById(id).orElse(null);
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public Country save(Country country) {
        try {
            return countryRepository.save(country);
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public boolean deleteById(Integer id) {
        try {
            countryRepository.deleteById(id);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public Country update(Country country) {
        try {
            return countryRepository.save(country);
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public List<Country> findByName(String name) {
        return countryRepository.findByCountryNameContainingIgnoreCase(name);
    }
}
