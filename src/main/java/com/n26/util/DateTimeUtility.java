package com.n26.util;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.time.temporal.ChronoUnit;

public class DateTimeUtility {

    private DateTimeUtility() {}

    public static LocalDateTime convertToDateTime(final String timeStamp) {
        Instant instant = Instant.parse(timeStamp);
        return LocalDateTime.ofInstant(instant, ZoneId.of(ZoneOffset.UTC.getId()));
    }

    public static boolean isMoreThanSeconds(final LocalDateTime transactionDate, final int thresholdSeconds) {
        long seconds = ChronoUnit.SECONDS.between(transactionDate, currentDateTime());
        return seconds >= thresholdSeconds;
    }

    public static LocalDateTime currentDateTime() {
        ZonedDateTime dateTime = ZonedDateTime.ofInstant(Instant.now(), ZoneId.of(ZoneOffset.UTC.getId()));
        return dateTime.toLocalDateTime();
    }
}
