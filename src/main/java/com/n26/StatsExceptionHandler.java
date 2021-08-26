package com.n26;

import com.n26.exceptions.FutureTransactionException;
import com.n26.exceptions.InvalidTransactionAmountException;
import com.n26.exceptions.PastTransactionException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.format.DateTimeParseException;

@ControllerAdvice
@Slf4j
public class StatsExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler({DateTimeParseException.class, FutureTransactionException.class,
            NumberFormatException.class, InvalidTransactionAmountException.class})
    public ResponseEntity<Object> handleDateTimeParse(Exception exception) {
        log.error("Error occurred", exception);
        return ResponseEntity.unprocessableEntity().build();
    }

    @ExceptionHandler(PastTransactionException.class)
    public ResponseEntity<Object> handlePastDateException(PastTransactionException exception) {
        log.error("Past Transaction date error occurred", exception);
        return ResponseEntity.noContent().build();
    }
}
