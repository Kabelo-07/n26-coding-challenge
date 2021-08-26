package com.n26.transaction.model;

import lombok.Getter;

@Getter
public enum TransactionStatus {
    SUCCESSFUL(201),
    INVALID_AMOUNT(422),
    FUTURE_DATED(422),
    PAST_DATED(204);

    private final int statusCode;
    TransactionStatus(final int status){
        this.statusCode = status;
    }
}
