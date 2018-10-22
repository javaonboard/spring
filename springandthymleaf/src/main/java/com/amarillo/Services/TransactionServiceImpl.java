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
    public Transaction updateTransaction(Transaction transaction) {
       Optional<Transaction> tr = transactionRepository.findById(transaction.getId());
        tr.get().setDay(transaction.getDay());
        tr.get().setDescription(transaction.getDescription());
        tr.get().setType(transaction.getType());
        return tr.get();
    }

    @Override
    public void deleteTransaction(Transaction transaction) {
        transactionRepository.delete(transaction);
    }

    @Override
    public Iterable<Transaction> getAllTransaction() {
        return transactionRepository.findAll();
    }

    @Override
    public List<Transaction> getTransactionByDay(String day) {
        return transactionRepository.getAllTransactionByDay(day);
    }
}
