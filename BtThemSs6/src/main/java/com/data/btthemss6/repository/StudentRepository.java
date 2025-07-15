package com.data.btthemss6.repository;

import com.data.btthemss6.entity.Students;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepository extends JpaRepository<Students, Long> {
}
