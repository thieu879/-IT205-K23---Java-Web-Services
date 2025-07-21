package com.data.ss10.exception;

public class CreditCardAlreadyExistsException extends RuntimeException {
    public CreditCardAlreadyExistsException(String message) {
        super(message);
    }
}
