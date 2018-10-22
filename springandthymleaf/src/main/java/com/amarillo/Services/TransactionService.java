package com.amarillo.Services;

import com.amarillo.entity.Transaction;

import java.util.List;

public interface TransactionService {

    String createTransaction(Transaction transaction);
    Transaction updateTransaction(Transaction transaction);
    void deleteTransaction(Transaction transaction);
    Iterable<Transaction> getAllTransaction();
    List<Transaction> getTransactionByDay(String day);

}
