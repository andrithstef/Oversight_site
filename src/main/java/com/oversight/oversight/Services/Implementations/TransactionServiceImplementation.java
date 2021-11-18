package com.oversight.oversight.Services.Implementations;

import com.oversight.oversight.Persistence.Entities.Transaction;
import com.oversight.oversight.Persistence.Entities.User;
import com.oversight.oversight.Persistence.Repositories.TransactionRepository;
import com.oversight.oversight.Services.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

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

    @Override
    public List<Transaction> findAllByUser(User user) {
        return transactionRepository.findAllByUser(user);
    }

    @Override
    public ArrayList<ArrayList<Object>> getChartData(User user) {

        System.out.println("What the fuck???");

        //Create empty list
        ArrayList<ArrayList<Object>> chartData = new ArrayList<ArrayList<Object>>();

        //get all transactions
        List<Transaction> transactions = transactionRepository.findAllByUser(user);

        //Add each transaction to the empty list as a pair of category and amount
        for (Transaction t : transactions){
            String s = t.getCategory();
            int i = t.getAmount();
            System.out.println(s);
            System.out.println(i);
            ArrayList<Object> temp = new ArrayList<Object>();
            temp.add(s);
            temp.add(i);
            chartData.add(temp);
        }

        return chartData;
    }
}
