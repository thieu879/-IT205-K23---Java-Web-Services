package com.data.btthemss3.entity;

import jakarta.persistence.*;


import java.util.List;
import java.util.ArrayList;

@Entity
@Table(name = "countries")
public class Country {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "country_id")
    private Integer countryId;

    @Column(name = "country_name", nullable = false, length = 100)
    private String countryName;

    @Column(name = "continental", length = 50)
    private String continental;

    // Mối quan hệ 1-n với City
    @OneToMany(mappedBy = "country", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<City> cities = new ArrayList<>();

    // Constructors
    public Country() {}

    public Country(String countryName, String continental) {
        this.countryName = countryName;
        this.continental = continental;
    }

    // Getters and Setters
    public Integer getCountryId() {
        return countryId;
    }

    public void setCountryId(Integer countryId) {
        this.countryId = countryId;
    }

    public String getCountryName() {
        return countryName;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

    public String getContinental() {
        return continental;
    }

    public void setContinental(String continental) {
        this.continental = continental;
    }

    public List<City> getCities() {
        return cities;
    }

    public void setCities(List<City> cities) {
        this.cities = cities;
    }

    // Utility methods
    public void addCity(City city) {
        cities.add(city);
        city.setCountry(this);
    }

    public void removeCity(City city) {
        cities.remove(city);
        city.setCountry(null);
    }
}

