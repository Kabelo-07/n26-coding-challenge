package com.n26.api;

import com.n26.transaction.model.TransactionModel;
import com.n26.transaction.model.TransactionStatus;
import com.n26.transaction.service.TransactionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class TransactionResource {

    private final TransactionService transactionService;

    @PostMapping("/transactions")
    public ResponseEntity<Void> createTransaction(@RequestBody final TransactionModel transactionModel) {
        TransactionStatus status = transactionService.createTransaction(transactionModel);
        return ResponseEntity.status(status.getStatusCode()).build();
    }

    @DeleteMapping("/transactions")
    public ResponseEntity<Void> deleteTransactions() {
        transactionService.deleteTransactions();
        return ResponseEntity.noContent().build();
    }
}
