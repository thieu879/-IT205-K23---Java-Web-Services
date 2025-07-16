package com.data.ss7.repository;

import com.data.ss7.model.Seed;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SeedRepository extends JpaRepository<Seed,Long> {

}