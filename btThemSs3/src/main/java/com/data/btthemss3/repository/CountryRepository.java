package com.data.btthemss3.repository;

import com.data.btthemss3.entity.Country;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CountryRepository extends JpaRepository<Country, Integer> {
    List<Country> findByCountryNameContainingIgnoreCase(String countryName);
}
