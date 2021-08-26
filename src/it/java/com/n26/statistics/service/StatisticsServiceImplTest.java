package com.n26.statistics.service;

import com.n26.TestTrxData;
import com.n26.configs.TransactionConfigProperties;
import com.n26.repository.TransactionServiceRepository;
import com.n26.statistics.StatisticsCalculator;
import com.n26.statistics.model.StatisticsModel;
import com.n26.transaction.model.TransactionModel;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class StatisticsServiceImplTest {

    private StatisticsService statisticsService;

    @Mock private TransactionServiceRepository repository;
    @Mock TransactionConfigProperties configProperties;

    @Before
    public void setUp() throws Exception {
        statisticsService = new StatisticsServiceImpl(repository, new StatisticsCalculator(), configProperties);
    }

    @Test
    public void test_getStatisticsWhenTransactionDataIsNotAvailable() {
        when(repository.isEmpty()).thenReturn(true);
        StatisticsModel model = statisticsService.getStatistics();
        assertEquals(StatisticsModel.defaultInstance(), model);
    }

    @Test
    public void test_getStatisticsWhenTransactionDataIsAvailable() {
        ConcurrentMap<String, TransactionModel> map = new ConcurrentHashMap<>();
        map.put(UUID.randomUUID().toString(),TestTrxData.testTrxModel1());
        map.put(UUID.randomUUID().toString(), TestTrxData.testTrxModel3());
        when(repository.isEmpty()).thenReturn(false);

        when(repository.findAll()).thenReturn(map);
        when(configProperties.getThresholdSeconds()).thenReturn(60);
        StatisticsModel model = statisticsService.getStatistics();
        assertEquals(2, model.getCount());
        assertEquals(3500.00D, model.getMax().doubleValue(), 0);
    }
}
