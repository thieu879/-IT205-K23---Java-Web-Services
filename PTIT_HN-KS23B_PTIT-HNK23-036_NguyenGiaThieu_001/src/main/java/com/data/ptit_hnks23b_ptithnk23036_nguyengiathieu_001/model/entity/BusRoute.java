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
@Table(name = "bus_route")
public class BusRoute {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int busRouteId;

    @Column(nullable = false, length = 255)
    private String startPoint;

    @Column(nullable = false, length = 255)
    private String endPoint;

    @Column(nullable = false, length = 255)
    private String tripInformation;

    @Column(nullable = false, length = 70)
    private String driverName;

    @Column(nullable = false)
    private boolean status;

    @ManyToOne
    @JoinColumn(name = "bus_id", nullable = false)
    private Bus bus;
}