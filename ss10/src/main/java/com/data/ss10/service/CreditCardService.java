package com.data.ss10.service;

import com.data.ss10.exception.AccountNotFoundException;
import com.data.ss10.exception.CreditCardAlreadyExistsException;
import com.data.ss10.exception.CreditCardNotFoundException;
import com.data.ss10.model.entity.Account;
import com.data.ss10.model.entity.CreditCard;
import com.data.ss10.repository.AccountRepository;
import com.data.ss10.repository.CreditCardRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@Transactional
public class CreditCardService {
    private final CreditCardRepository creditCardRepository;
    private final AccountRepository accountRepository;

    public CreditCardService(CreditCardRepository creditCardRepository,
                             AccountRepository accountRepository) {
        this.creditCardRepository = creditCardRepository;
        this.accountRepository = accountRepository;
    }

    public CreditCard createCreditCard(UUID accountId, Double spendingLimit) {
        // Kiểm tra account có tồn tại không
        Account account = accountRepository.findById(accountId)
                .orElseThrow(() -> new AccountNotFoundException("Account not found"));

        // Kiểm tra account đã có thẻ tín dụng chưa
        if (creditCardRepository.existsByAccount_Id(accountId)) {
            throw new CreditCardAlreadyExistsException("Account already has a credit card");
        }

        CreditCard creditCard = new CreditCard();
        creditCard.setAccount(account);
        creditCard.setSpendingLimit(spendingLimit);
        creditCard.setAmountSpent(0.0);
        creditCard.setStatus("active");

        return creditCardRepository.save(creditCard);
    }

    public CreditCard updateSpendingLimit(UUID id, Double newLimit) {
        CreditCard creditCard = creditCardRepository.findById(id)
                .orElseThrow(() -> new CreditCardNotFoundException("Credit card not found"));

        creditCard.setSpendingLimit(newLimit);
        return creditCardRepository.save(creditCard);
    }

    public CreditCard updateStatus(UUID id, String status) {
        CreditCard creditCard = creditCardRepository.findById(id)
                .orElseThrow(() -> new CreditCardNotFoundException("Credit card not found"));

        if (!status.equals("active") && !status.equals("inactive")) {
            throw new IllegalArgumentException("Status must be 'active' or 'inactive'");
        }

        creditCard.setStatus(status);
        return creditCardRepository.save(creditCard);
    }
}
