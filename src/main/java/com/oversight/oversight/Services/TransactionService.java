package com.oversight.oversight.Services;

import com.oversight.oversight.Persistence.Entities.Transaction;

import java.util.List;

public interface TransactionService {
    Transaction findByID(long ID);
    List<Transaction> findAll();
    Transaction save(Transaction transaction);
    void delete(Transaction transaction);
}
