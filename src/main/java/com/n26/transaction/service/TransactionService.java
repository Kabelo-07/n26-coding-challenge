package com.n26.transaction.service;

import com.n26.transaction.model.TransactionModel;
import com.n26.transaction.model.TransactionStatus;

public interface TransactionService {
    TransactionStatus createTransaction(final TransactionModel transactionModel);

    void deleteTransactions();
}
