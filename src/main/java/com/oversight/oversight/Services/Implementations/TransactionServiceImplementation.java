package com.oversight.oversight.Services.Implementations;

import com.oversight.oversight.Persistence.Entities.Transaction;
import com.oversight.oversight.Persistence.Repositories.TransactionRepository;
import com.oversight.oversight.Services.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TransactionServiceImplementation implements TransactionService {
    private TransactionRepository transactionRepository;

    @Autowired
    public TransactionServiceImplementation(TransactionRepository transactionRepository){
        this.transactionRepository = transactionRepository;
    }


    @Override
    public Transaction findByID(long ID) {
        return transactionRepository.findByID(ID);
    }

    @Override
    public List<Transaction> findAll() {
        return transactionRepository.findAll();
    }

    @Override
    public Transaction save(Transaction transaction) {
        return transactionRepository.save(transaction);
    }

    @Override
    public void delete(Transaction transaction) {
        transactionRepository.delete(transaction);
    }
}
