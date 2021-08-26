package com.n26.statistics;

import com.n26.statistics.model.StatisticsModel;
import com.n26.transaction.model.TransactionModel;
import lombok.Getter;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.Comparator;

@Component
@Getter
public class StatisticsCalculator {

    public StatisticsModel calculate(final Collection<TransactionModel> list) {
        if (CollectionUtils.isEmpty(list)) {
            return StatisticsModel.defaultInstance();
        }
        BigDecimal sum = BigDecimal.valueOf(list.stream().mapToDouble(value -> value.getAmountAsBigDecimal().doubleValue()).sum());

        int count = list.size();
        BigDecimal avg = BigDecimal.valueOf(sum.doubleValue() / count);

        return StatisticsModel.builder()
                .avg(avg).count(count).sum(sum)
                .max(this.getMaximum(list))
                .min(this.getMinimum(list))
                .build();
    }

    private BigDecimal getMinimum(final Collection<TransactionModel> list) {
        return list.stream().min(Comparator.comparing(TransactionModel::getAmountAsBigDecimal))
                .orElse(TransactionModel.defaultInstance()).getAmountAsBigDecimal();
    }

    private BigDecimal getMaximum(final Collection<TransactionModel> list) {
        return list.stream().max(Comparator.comparing(TransactionModel::getAmountAsBigDecimal))
                .orElse(TransactionModel.defaultInstance()).getAmountAsBigDecimal();
    }
}
