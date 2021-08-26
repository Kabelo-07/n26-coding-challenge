package com.n26.transaction.service;

import com.n26.TestTrxData;
import com.n26.configs.TransactionConfigProperties;
import com.n26.repository.TransactionServiceRepository;
import com.n26.transaction.model.TransactionModel;
import com.n26.transaction.model.TransactionStatus;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.util.UUID;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class TransactionServiceImplTest {

    TransactionService transactionService;

    @Mock
    TransactionServiceRepository repository;

    @Mock
    TransactionConfigProperties configProperties;

    @Before
    public void setUp() throws Exception {
        transactionService = new TransactionServiceImpl(repository, configProperties);
    }

    @Test
    public void testCreateTransaction() {
        when(configProperties.getThresholdSeconds()).thenReturn(60);
        when(repository.save(any())).thenReturn(UUID.randomUUID());
        TransactionStatus status = transactionService.createTransaction(TestTrxData.testTrxModel1());
        assertEquals(HttpStatus.CREATED.value(), status.getStatusCode());
    }

    @Test
    public void test_createTransaction_WhenInvalidAmountPassedShouldFail() {
        TransactionStatus status =transactionService.createTransaction(TestTrxData.testTrxModelInvalidAmount());
        assertEquals(HttpStatus.UNPROCESSABLE_ENTITY.value(), status.getStatusCode());
    }

    @Test
    public void test_createTransaction_WhenFutureDatePassedShouldFail() {
        TransactionModel model = TestTrxData.testTrxModel1();
        model.setTimestamp(LocalDateTime.ofInstant(Instant.now().plusSeconds(5), ZoneId.of(ZoneOffset.UTC.getId())).toString().concat("Z"));
        TransactionStatus status =transactionService.createTransaction(model);
        assertEquals(HttpStatus.UNPROCESSABLE_ENTITY.value(), status.getStatusCode());
    }

    @Test
    public void test_createTransaction_WhenPastDatePassedShouldFail() {
        TransactionModel model = TestTrxData.testTrxModel1();
        model.setTimestamp(LocalDateTime.ofInstant(Instant.now().minusSeconds(5), ZoneId.of(ZoneOffset.UTC.getId())).toString().concat("Z"));

        TransactionStatus status =transactionService.createTransaction(model);
        assertEquals(HttpStatus.NO_CONTENT.value(), status.getStatusCode());
    }

    @Test
    public void testDeleteTransactions() {
        transactionService.deleteTransactions();
        verify(repository, times(1)).deleteAll();
    }
}
