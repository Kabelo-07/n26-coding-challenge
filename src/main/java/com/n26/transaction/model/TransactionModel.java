package com.n26.transaction.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.n26.util.DateTimeUtility;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.StringUtils;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.StringJoiner;

@Getter
@Setter
public class TransactionModel {
    private String amount;
    private String timestamp;

    public static TransactionModel defaultInstance() {
        return new TransactionModel();
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", TransactionModel.class.getSimpleName() + "[", "]")
                .add("amount=" + amount)
                .add("timestamp='" + timestamp + "'")
                .toString();
    }

    @JsonIgnore
    public LocalDateTime getTimeStampAsDate() {
        return DateTimeUtility.convertToDateTime(timestamp);
    }

    @JsonIgnore
    public BigDecimal getAmountAsBigDecimal() {
        if (StringUtils.isBlank(amount)) {
            return BigDecimal.ZERO;
        }
        return BigDecimal.valueOf(Double.parseDouble(amount));
    }

    @JsonIgnore
    public boolean hasValidAmount() {
        try {
            this.getAmountAsBigDecimal();
            return true;
        } catch (Exception ex) {
            return false;
        }
    }

    @JsonIgnore
    public boolean isFutureDated() {
        return getTimeStampAsDate().isAfter(DateTimeUtility.currentDateTime());
    }
}
