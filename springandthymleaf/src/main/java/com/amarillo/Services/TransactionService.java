package com.amarillo.Services;

import com.amarillo.entity.Transaction;

import java.util.List;
import java.util.Optional;

public interface TransactionService {

    String createTransaction(Transaction transaction);
    void updateTransaction(Transaction transaction);
    void deleteTransaction(Transaction transaction);
    void deleteTransactionById(Long id);
    Iterable<Transaction> getAllTransaction();
    List<Transaction> getTransactionByDay(String day);
    Optional<Transaction> getTransactionById(Long id);

}
