package com.amarillo.Services;

import com.amarillo.entity.Transaction;
import com.amarillo.repository.RevenueRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RevenueServiceImpl implements RevenueService {

    @Autowired
    RevenueRepository revenueRepository;

    @Override
    public Transaction createRevenue(Transaction transaction) {
        return null;
    }

    @Override
    public Transaction updateRevenue(Transaction transaction) {
        return null;
    }

    @Override
    public void deleteRevenue(Transaction transaction) {

    }

    @Override
    public Iterable<Transaction> getAllRevenue() {
        return revenueRepository.findAll();
    }

    @Override
    public List<Transaction> getRevenueByDay(String day) {
        return null;
    }
}
