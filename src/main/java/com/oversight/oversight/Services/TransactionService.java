package com.oversight.oversight.Services;

import com.oversight.oversight.Persistence.Entities.SpendingPlan;
import com.oversight.oversight.Persistence.Entities.Transaction;
import com.oversight.oversight.Persistence.Entities.User;

import java.util.ArrayList;
import java.util.List;

public interface TransactionService {
    Transaction save(Transaction transaction);
    void delete(Transaction transaction);

    List<Transaction> generateTransactions(User user);

    List<Transaction> findAllByUser(User user);
    List<Transaction> findAllByUserByCategory(User user);
    List<Transaction> findAllByUserByAmount(User user);

    ArrayList<ArrayList<Object>> getPieChartData(User user);
    ArrayList<ArrayList<Object>> getLineChartData(User user);
    ArrayList<ArrayList<Object>> getLineChartDataPlan(User user, SpendingPlan sp);

    Transaction findByID(long ID);
}
