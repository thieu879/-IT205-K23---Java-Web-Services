package com.data.ss10.repository;

import com.data.ss10.model.entity.CreditCard;
import com.data.ss10.model.entity.TransactionCredit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Repository
public interface TransactionCreditRepository extends JpaRepository<TransactionCredit, UUID> {
    List<TransactionCredit> findByCreditCardSenderAndCreatedAtBetween(
            CreditCard creditCard, LocalDateTime from, LocalDateTime to);
}
