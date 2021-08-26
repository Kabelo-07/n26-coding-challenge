package com.n26.exceptions;

public class PastTransactionException extends RuntimeException {

    public PastTransactionException() { }

    public PastTransactionException(String message) {
        super(message);
    }
}
