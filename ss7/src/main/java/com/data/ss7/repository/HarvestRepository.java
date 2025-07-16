package com.data.ss7.repository;

import com.data.ss7.model.Harvest;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HarvestRepository extends JpaRepository<Harvest,Long> {
}
