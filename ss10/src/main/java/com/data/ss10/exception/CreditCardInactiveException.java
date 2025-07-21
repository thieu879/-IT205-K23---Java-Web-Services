package com.data.ss10.exception;

public class CreditCardInactiveException extends RuntimeException {
    public CreditCardInactiveException(String message) {
        super(message);
    }
}
