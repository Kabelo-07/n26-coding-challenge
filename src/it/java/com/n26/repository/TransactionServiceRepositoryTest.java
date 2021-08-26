package com.n26.repository;

import com.n26.TestTrxData;
import com.n26.transaction.model.TransactionModel;
import org.junit.Before;
import org.junit.Test;

import java.util.UUID;

import static org.junit.Assert.*;

public class TransactionServiceRepositoryTest {

    private AbstractRepository<TransactionModel> repository;

    @Before
    public void setUp() {
        repository = new TransactionServiceRepository();
    }

    @Test
    public void save() {
        UUID uuid = repository.save(TestTrxData.testTrxModel1());
        assertNotNull(uuid);
        assertFalse(repository.isEmpty());
    }

    @Test
    public void deleteAll() {
        repository.save(TestTrxData.testTrxModel1());
        assertFalse(repository.isEmpty());

        repository.deleteAll();
        assertTrue(repository.isEmpty());
    }

    @Test
    public void findAll() {
        repository.save(TestTrxData.testTrxModel1());
        assertEquals(1, repository.findAll().size());
    }
}
