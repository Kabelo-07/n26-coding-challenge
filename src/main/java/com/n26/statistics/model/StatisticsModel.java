package com.n26.statistics.model;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.math.BigDecimal;

@Getter
@Setter
@Builder
@EqualsAndHashCode
@ToString
public class StatisticsModel {
    private BigDecimal sum;
    private BigDecimal avg;
    private BigDecimal max;
    private BigDecimal min;
    private int count;

    public static StatisticsModel defaultInstance() {
        return StatisticsModel.builder()
                .sum(BigDecimal.ZERO)
                .min(BigDecimal.ZERO)
                .max(BigDecimal.ZERO)
                .avg(BigDecimal.ZERO)
                .count(0)
                .build();
    }
}
