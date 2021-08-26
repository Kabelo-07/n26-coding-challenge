package com.n26.transaction.service;

import com.n26.configs.TransactionConfigProperties;
import com.n26.repository.TransactionServiceRepository;
import com.n26.transaction.model.TransactionModel;
import com.n26.transaction.model.TransactionStatus;
import com.n26.util.DateTimeUtility;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class TransactionServiceImpl implements TransactionService {

    private final TransactionServiceRepository repository;
    private final TransactionConfigProperties configProperties;

    @Override
    public TransactionStatus createTransaction(final TransactionModel transactionModel) {
        if(!transactionModel.hasValidAmount() || transactionModel.isFutureDated()) {
            return TransactionStatus.FUTURE_DATED;
        }

        if (this.transactionDateHasPassedThresholdSeconds(transactionModel)) {
            return TransactionStatus.PAST_DATED;
        }

        final UUID id = repository.save(transactionModel);
        log.info("Transaction {} created with Id {}", transactionModel, id);
        return TransactionStatus.SUCCESSFUL;
    }

    @Override
    public void deleteTransactions() {
        repository.deleteAll();
    }

    private boolean transactionDateHasPassedThresholdSeconds(TransactionModel transactionModel) {
        LocalDateTime transactionDate = transactionModel.getTimeStampAsDate();
        return DateTimeUtility.isMoreThanSeconds(transactionDate, configProperties.getThresholdSeconds());
    }

}
