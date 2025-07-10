package com.data.btthemss3.entity;

import jakarta.persistence.*;

import java.math.BigDecimal;

@Entity
@Table(name = "cities")
public class City {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "city_id")
    private Integer cityId;

    @Column(name = "city_name", nullable = false, length = 100)
    private String cityName;

    @Column(name = "sesson", length = 50)
    private String sesson;

    @Column(name = "area", precision = 18, scale = 2)
    private BigDecimal area;

    @Column(name = "population")
    private Long population;

    // Foreign Key
    @Column(name = "country_id", nullable = false)
    private Integer countryId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "country_id", insertable = false, updatable = false)
    private Country country;

    // Constructors
    public City() {}

    public City(String cityName, String sesson, BigDecimal area, Long population) {
        this.cityName = cityName;
        this.sesson = sesson;
        this.area = area;
        this.population = population;
    }

    public Integer getCityId() {
        return cityId;
    }

    public void setCityId(Integer cityId) {
        this.cityId = cityId;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public String getSesson() {
        return sesson;
    }

    public void setSesson(String sesson) {
        this.sesson = sesson;
    }

    public BigDecimal getArea() {
        return area;
    }

    public void setArea(BigDecimal area) {
        this.area = area;
    }

    public Long getPopulation() {
        return population;
    }

    public void setPopulation(Long population) {
        this.population = population;
    }

    public Integer getCountryId() {
        return countryId;
    }

    public void setCountryId(Integer countryId) {
        this.countryId = countryId;
    }

    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
        if (country != null) {
            this.countryId = country.getCountryId();
        }
    }
}

