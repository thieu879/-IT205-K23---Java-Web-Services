package com.data.ptit_hnks23b_ptithnk23036_nguyengiathieu_001.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "bus")
public class Bus {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int busId;

    @Column(nullable = false, unique = true, length = 100)
    private String busName;

    @Column(nullable = false, unique = true, length = 30)
    private String registrationNumber;

    @Column(nullable = false)
    private int totalSeats;

    @Column(length = 255)
    private String imageBus;

    @Column(nullable = false, columnDefinition = "BIT DEFAULT 1")
    private boolean status;
}