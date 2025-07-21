package com.data.ss10.exception;

public class InsufficientCreditLimitException extends RuntimeException {
    public InsufficientCreditLimitException(String message) {
        super(message);
    }
}
