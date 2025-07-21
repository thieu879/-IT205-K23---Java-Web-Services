package com.data.ss10.exception;

public class CreditCardNotFoundException extends RuntimeException {
    public CreditCardNotFoundException(String message) {
        super(message);
    }
}
