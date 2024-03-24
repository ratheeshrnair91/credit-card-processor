package com.domain.creditcardprocessor.exception;

/**
 * Custom exception for invalid card number
 */
public class InvalidCreditCardNumberException extends RuntimeException {
    public InvalidCreditCardNumberException(String message) {
        super(message);
    }
}
