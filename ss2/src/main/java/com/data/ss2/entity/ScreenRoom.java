package com.data.ss2.entity;

import jakarta.persistence.*;

@Entity
public class ScreenRoom {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private Integer capacity;
    @ManyToOne
    @JoinColumn(name = "theater_id", nullable = false)
    private Theater theater;

    public ScreenRoom() {
    }
    public ScreenRoom(String name, Integer capacity, Theater theater) {
        this.name = name;
        this.capacity = capacity;
        this.theater = theater;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getCapacity() {
        return capacity;
    }

    public void setCapacity(Integer capacity) {
        this.capacity = capacity;
    }

    public Theater getTheater() {
        return theater;
    }

    public void setTheater(Theater theater) {
        this.theater = theater;
    }
}
