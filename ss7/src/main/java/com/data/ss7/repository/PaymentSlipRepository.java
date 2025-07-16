package com.data.ss7.repository;

import com.data.ss7.model.PaymentSlip;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PaymentSlipRepository extends JpaRepository<PaymentSlip, Long> {
}