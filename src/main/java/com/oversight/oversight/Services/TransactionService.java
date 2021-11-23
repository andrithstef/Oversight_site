package com.oversight.oversight.Services;

import com.oversight.oversight.Persistence.Entities.Transaction;
import com.oversight.oversight.Persistence.Entities.User;

import java.time.Month;
import java.util.ArrayList;
import java.util.List;

public interface TransactionService {
    Transaction findByID(long ID);
    List<Transaction> findAll();
    Transaction save(Transaction transaction);
    void delete(Transaction transaction);
    List<Transaction> findAllByUser(User user);
    ArrayList<ArrayList<Object>> getPieChartData(User user, Month currentMonth);
    ArrayList<ArrayList<Object>> getLineChartData(User user);
}
