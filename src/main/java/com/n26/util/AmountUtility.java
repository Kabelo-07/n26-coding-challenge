package com.n26.util;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class AmountUtility {

    public static BigDecimal roundHalfUp(BigDecimal value) {
        if (null == value) {
            value = BigDecimal.ZERO;
        }
        return value.setScale(2, RoundingMode.HALF_UP);
    }
}
