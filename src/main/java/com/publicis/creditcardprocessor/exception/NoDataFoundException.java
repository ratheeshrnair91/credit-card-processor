package com.publicis.creditcardprocessor.exception;

/**
 * Custom exception for no data found
 */
public class NoDataFoundException extends RuntimeException {
    public NoDataFoundException(String message) {
        super(message);
    }
}
