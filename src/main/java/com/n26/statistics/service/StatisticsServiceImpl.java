package com.n26.statistics.service;

import com.n26.configs.TransactionConfigProperties;
import com.n26.repository.TransactionServiceRepository;
import com.n26.statistics.StatisticsCalculator;
import com.n26.statistics.model.StatisticsModel;
import com.n26.transaction.model.TransactionModel;
import com.n26.util.DateTimeUtility;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
class StatisticsServiceImpl implements StatisticsService {

    private final TransactionServiceRepository repository;
    private final StatisticsCalculator statisticsCalculator;
    private final TransactionConfigProperties configProperties;

    public StatisticsModel getStatistics() {
        if (repository.isEmpty()) {
            return StatisticsModel.defaultInstance();
        }
        StatisticsModel model = this.aggregateStatistics();
        log.info("Statistics collected={}", model);
        return model;
    }

    private StatisticsModel aggregateStatistics() {
        for (String id: repository.findAll().keySet()) {
            TransactionModel transactionModel = repository.findAll().get(id);
            if (DateTimeUtility.isMoreThanSeconds(transactionModel.getTimeStampAsDate(), configProperties.getThresholdSeconds())) {
                repository.findAll().remove(id);
            }
        }
        return statisticsCalculator.calculate(repository.findAll().values());
    }
}
