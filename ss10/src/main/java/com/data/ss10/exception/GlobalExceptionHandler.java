package com.data.ss10.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(AccountNotFoundException.class)
    public ResponseEntity<String> handleNotFound(AccountNotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Account not found");
    }

    @ExceptionHandler(CreditCardNotFoundException.class)
    public ResponseEntity<String> handleCreditCardNotFound(CreditCardNotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Credit card not found");
    }

    @ExceptionHandler(CreditCardAlreadyExistsException.class)
    public ResponseEntity<String> handleCreditCardAlreadyExists(CreditCardAlreadyExistsException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Account already has a credit card");
    }

    @ExceptionHandler(InsufficientCreditLimitException.class)
    public ResponseEntity<String> handleInsufficientCreditLimit(InsufficientCreditLimitException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Insufficient credit limit");
    }

    @ExceptionHandler(CreditCardInactiveException.class)
    public ResponseEntity<String> handleCreditCardInactive(CreditCardInactiveException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Credit card is inactive");
    }

    @ExceptionHandler(org.springframework.web.bind.MethodArgumentNotValidException.class)
    public ResponseEntity<String> handleBadRequest(Exception ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid data");
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<String> handleIllegalArgument(IllegalArgumentException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleServerError(Exception ex) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Server error");
    }
}
