package com.n26.repository;

import com.n26.transaction.model.TransactionModel;
import org.springframework.stereotype.Repository;

import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

@Repository
public class TransactionServiceRepository implements AbstractRepository<TransactionModel> {
    private final ConcurrentHashMap<String, TransactionModel> transactionMap = new ConcurrentHashMap<>();

    @Override
    public UUID save(final TransactionModel transactionModel) {
        UUID id = UUID.randomUUID();
        transactionMap.put(id.toString(), transactionModel);
        return id;
    }

    @Override
    public void deleteAll() {
        transactionMap.clear();
    }

    @Override
    public ConcurrentMap<String, TransactionModel> findAll() {
        return transactionMap;
    }

    @Override
    public boolean isEmpty() {
        return transactionMap.isEmpty();
    }
}
