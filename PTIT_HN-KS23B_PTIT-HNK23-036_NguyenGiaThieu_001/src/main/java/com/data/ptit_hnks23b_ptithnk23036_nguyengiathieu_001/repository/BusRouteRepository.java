package com.data.ptit_hnks23b_ptithnk23036_nguyengiathieu_001.repository;

import com.data.ptit_hnks23b_ptithnk23036_nguyengiathieu_001.model.entity.BusRoute;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BusRouteRepository extends JpaRepository<BusRoute, Integer> {
    List<BusRoute> findByStartPointContainingAndEndPointContaining(String startPoint, String endPoint);
}