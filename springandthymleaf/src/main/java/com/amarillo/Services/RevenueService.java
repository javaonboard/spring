package com.amarillo.Services;

import com.amarillo.entity.Transaction;

import java.util.List;

public interface RevenueService {

    Transaction createRevenue(Transaction transaction);
    Transaction updateRevenue(Transaction transaction);
    void deleteRevenue(Transaction transaction);
    Iterable<Transaction> getAllRevenue();
    List<Transaction> getRevenueByDay(String day);

}
