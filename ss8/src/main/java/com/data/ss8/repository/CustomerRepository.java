package com.data.ss8.repository;

import com.data.ss8.model.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {
    List<Customer> findByStatus(Boolean status);
    Optional<Customer> findByIdAndStatus(Long id, Boolean status);
    Optional<Customer> findByEmail(String email);
    Optional<Customer> findByPhone(String phone);
    @Query("SELECT c FROM Customer c WHERE c.status = true")
    List<Customer> findActiveCustomers();
}
