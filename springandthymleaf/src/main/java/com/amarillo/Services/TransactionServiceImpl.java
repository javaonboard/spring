package com.amarillo.Services;

import com.amarillo.entity.Transaction;
import com.amarillo.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TransactionServiceImpl implements TransactionService {

    @Autowired
    TransactionRepository transactionRepository;


    @Override
    public String createTransaction(Transaction transaction) {
        boolean mybool = false;
        try {
            transactionRepository.save(transaction);
        }catch(Exception e){
            mybool = true;
        }
        return mybool?"Failed.":"Successful.";
    }

    @Override
    public void updateTransaction(Transaction transaction) {
        Optional<Transaction> tr = transactionRepository.findById(transaction.getId());
        tr.get().setDescription(transaction.getDescription());
        tr.get().setAmount(transaction.getAmount());
    }

    @Override
    public void deleteTransaction(Transaction transaction) {
        transactionRepository.delete(transaction);
    }

    @Override
    public void deleteTransactionById(Long id) {
        transactionRepository.deleteById(id);
    }

    @Override
    public Iterable<Transaction> getAllTransaction() {
        return transactionRepository.findAll();
    }

    @Override
    public List<Transaction> getTransactionByDay(String day) {
        return transactionRepository.getAllTransactionByDay(day);
    }

    @Override
    public Optional<Transaction> getTransactionById(Long id) {
        return transactionRepository.findById(id);
    }
}
