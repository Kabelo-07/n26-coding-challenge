package com.n26.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.n26.TestTrxData;
import com.n26.transaction.model.TransactionStatus;
import com.n26.transaction.service.TransactionService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(MockitoJUnitRunner.class)
public class TransactionResourceTest {

    private TransactionResource transactionResource;
    @Mock private TransactionService transactionService;

    private MockMvc mockMvc;
    private ObjectMapper objectMapper = new ObjectMapper();

    @Before
    public void setUp() throws Exception {
        transactionResource = new TransactionResource(transactionService);
        mockMvc = MockMvcBuilders.standaloneSetup(transactionResource).build();
    }

    @Test
    public void test_createTransactionWhenSuccessful() throws Exception{
        when(transactionService.createTransaction(any())).thenReturn(TransactionStatus.SUCCESSFUL);
        mockMvc.perform(MockMvcRequestBuilders.post("/transactions")
                    .contentType(MediaType.APPLICATION_JSON).content(objectMapper.writeValueAsString(TestTrxData.testTrxModel1())))
        .andExpect(status().isCreated());
    }

    @Test
    public void test_createTransactionWhenInvalidAmountPassed() throws Exception{
        when(transactionService.createTransaction(any())).thenReturn(TransactionStatus.INVALID_AMOUNT);
        mockMvc.perform(MockMvcRequestBuilders.post("/transactions")
                .contentType(MediaType.APPLICATION_JSON).content(objectMapper.writeValueAsString(TestTrxData.testTrxModelInvalidAmount())))
                .andExpect(status().isUnprocessableEntity());
    }

    @Test
    public void test_createTransactionWhenFutureDatePassed() throws Exception{
        when(transactionService.createTransaction(any())).thenReturn(TransactionStatus.FUTURE_DATED);
        mockMvc.perform(MockMvcRequestBuilders.post("/transactions")
                .contentType(MediaType.APPLICATION_JSON).content(objectMapper.writeValueAsString(TestTrxData.testTrxModelInvalidAmount())))
                .andExpect(status().isUnprocessableEntity());
    }

    @Test
    public void test_createTransactionWhenOldDatePassed() throws Exception{
        when(transactionService.createTransaction(any())).thenReturn(TransactionStatus.PAST_DATED);
        mockMvc.perform(MockMvcRequestBuilders.post("/transactions")
                .contentType(MediaType.APPLICATION_JSON).content(objectMapper.writeValueAsString(TestTrxData.testTrxModelInvalidAmount())))
                .andExpect(status().isNoContent());
    }

    @Test
    public void deleteTransactions() throws Exception{
        mockMvc.perform(MockMvcRequestBuilders.delete("/transactions")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());
    }
}
