package com.n26;

import com.n26.transaction.model.TransactionModel;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class TestTrxData {

    public static TransactionModel testTrxModel1() {
        TransactionModel model = TransactionModel.defaultInstance();
        model.setAmount("53.6789");
        model.setTimestamp(LocalDateTime.ofInstant(Instant.now(), ZoneId.of(ZoneOffset.UTC.getId())).toString().concat("Z"));
        return model;
    }

    public static TransactionModel testTrxModelInvalidAmount() {
        TransactionModel model = TransactionModel.defaultInstance();
        model.setAmount("three grand");
        return model;
    }

    public static TransactionModel testTrxModel3() {
        TransactionModel model = TransactionModel.defaultInstance();
        model.setAmount("3500");
        model.setTimestamp(LocalDateTime.ofInstant(Instant.now(), ZoneId.of(ZoneOffset.UTC.getId())).toString().concat("Z"));
        return model;
    }

    public static TransactionModel testTrxModel2() {
        TransactionModel model = TransactionModel.defaultInstance();
        model.setAmount("53.6789");
        model.setTimestamp(LocalDateTime.ofInstant(Instant.now(), ZoneId.of(ZoneOffset.UTC.getId())).toString().concat("Z"));
        return model;
    }
}
