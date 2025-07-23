package com.data.ptit_hnks23b_ptithnk23036_nguyengiathieu_001.service;

import com.data.ptit_hnks23b_ptithnk23036_nguyengiathieu_001.model.entity.BusRoute;
import com.data.ptit_hnks23b_ptithnk23036_nguyengiathieu_001.repository.BusRouteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class BusRouteService {
    @Autowired
    private BusRouteRepository busRouteRepository;

    public List<BusRoute> getAllRoutes() {
        return busRouteRepository.findAll();
    }

    public BusRoute addRoute(BusRoute route) {
        return busRouteRepository.save(route);
    }

    public BusRoute updateRoute(int id, BusRoute route) {
        BusRoute existingRoute = busRouteRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Route not found"));
        existingRoute.setStartPoint(route.getStartPoint());
        existingRoute.setEndPoint(route.getEndPoint());
        existingRoute.setTripInformation(route.getTripInformation());
        existingRoute.setDriverName(route.getDriverName());
        existingRoute.setStatus(route.isStatus());
        return busRouteRepository.save(existingRoute);
    }

    public void deleteRoute(int id) {
        BusRoute route = busRouteRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Route not found"));
        route.setStatus(false);
        busRouteRepository.save(route);
    }

    public List<BusRoute> searchRoutes(String startPoint, String endPoint) {
        return busRouteRepository.findByStartPointContainingAndEndPointContaining(startPoint, endPoint);
    }
}