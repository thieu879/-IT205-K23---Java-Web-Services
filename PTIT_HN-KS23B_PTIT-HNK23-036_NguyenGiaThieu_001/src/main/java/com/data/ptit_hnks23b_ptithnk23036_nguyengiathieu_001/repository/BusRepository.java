package com.data.ptit_hnks23b_ptithnk23036_nguyengiathieu_001.repository;

import com.data.ptit_hnks23b_ptithnk23036_nguyengiathieu_001.model.entity.Bus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BusRepository extends JpaRepository<Bus, Integer> {
    boolean existsByBusName(String busName);
}