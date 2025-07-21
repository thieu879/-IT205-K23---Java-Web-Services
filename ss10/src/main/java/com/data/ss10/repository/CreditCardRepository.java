package com.data.ss10.repository;

import com.data.ss10.model.entity.CreditCard;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface CreditCardRepository extends JpaRepository<CreditCard, UUID> {
    Optional<CreditCard> findByAccount_Id(UUID accountId);
    boolean existsByAccount_Id(UUID accountId);
}
