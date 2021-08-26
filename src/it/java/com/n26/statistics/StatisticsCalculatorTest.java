package com.n26.statistics;

import com.n26.statistics.model.StatisticsModel;
import com.n26.util.AmountUtility;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Collections;

import static com.n26.TestTrxData.testTrxModel2;
import static com.n26.TestTrxData.testTrxModel3;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(MockitoJUnitRunner.class)
public class StatisticsCalculatorTest {

    private final StatisticsCalculator statisticsCalculator = new StatisticsCalculator();

    @Test
    public void test_calculateWhenCollectionIsEmptyOrNullShouldReturnEmptyStats() {
        StatisticsModel model = statisticsCalculator.calculate(null);
        assertNotNull(model);
        assertEquals(StatisticsModel.defaultInstance(), model);

        model = statisticsCalculator.calculate(Collections.emptyList());
        assertNotNull(model);
        assertEquals(StatisticsModel.defaultInstance(), model);
    }

    @Test
    public void test_calculateWhenCollectionHasTrxData() {
        StatisticsModel model = statisticsCalculator.calculate(Arrays.asList(testTrxModel3(), testTrxModel2()));
        assertEquals(2, model.getCount());
        assertEquals(BigDecimal.valueOf(53.68), AmountUtility.roundHalfUp(model.getMin()));
        assertEquals(3500.00, AmountUtility.roundHalfUp(model.getMax()).floatValue(), 0);
        assertEquals(BigDecimal.valueOf(3553.68), AmountUtility.roundHalfUp(model.getSum()));
        assertEquals(BigDecimal.valueOf(3553.68/2), AmountUtility.roundHalfUp(model.getAvg()));
    }
}
