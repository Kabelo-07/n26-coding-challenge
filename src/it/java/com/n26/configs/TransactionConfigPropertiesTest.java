package com.n26.configs;

import com.n26.Application;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("test")
public class TransactionConfigPropertiesTest {

    @Autowired TransactionConfigProperties properties;

    @Test
    public void testGetThresholdSeconds() {
        assertNotNull(properties);
        assertEquals(34, properties.getThresholdSeconds());
    }
}
