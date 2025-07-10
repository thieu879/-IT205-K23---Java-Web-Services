package com.data.btthemss3.repository;

import com.data.btthemss3.entity.City;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CityRepository extends JpaRepository<City, Integer> {
    List<City> findByCityNameContainingIgnoreCase(String cityName);
}
