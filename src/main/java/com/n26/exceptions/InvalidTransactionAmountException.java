package com.n26.exceptions;

public class InvalidTransactionAmountException extends RuntimeException {
    public InvalidTransactionAmountException(String message) {
        super(message);
    }
}
