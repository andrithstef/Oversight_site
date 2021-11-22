package com.oversight.oversight.Services.Implementations;

import com.oversight.oversight.Persistence.Entities.Transaction;
import com.oversight.oversight.Persistence.Entities.User;
import com.oversight.oversight.Persistence.Repositories.TransactionRepository;
import com.oversight.oversight.Services.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Month;
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
    public ArrayList<ArrayList<Object>> getPieChartData(User user) {

        //Create dictionary
        HashMap<String, Integer> map = new HashMap<String, Integer>();

        //Create empty list
        ArrayList<ArrayList<Object>> chartData = new ArrayList<ArrayList<Object>>();

        //get all transactions
        List<Transaction> transactions = transactionRepository.findAllByUser(user);

        //Add each transaction to the empty list as a pair of category and amount
        for (Transaction t : transactions){
            String s = t.getCategory();
            int i = t.getAmount();
            if(map.containsKey(s)){
                map.put(s, map.get(s)+i);
            }
            else{
                map.put(s, i);
            }
        }

        Iterator<Map.Entry<String, Integer>> entrySet = map.entrySet().iterator();

        while(entrySet.hasNext()){
            Map.Entry<String, Integer> entry = entrySet.next();
            ArrayList<Object> temp = new ArrayList<Object>();
            temp.add(entry.getKey());
            temp.add(entry.getValue());
            chartData.add(temp);
        }

        return chartData;
    }

    @Override
    public ArrayList<ArrayList<Object>> getLineChartData(User user) {
        //Initialize the data, by month and spending
        ArrayList<ArrayList<Object>> chartData = new ArrayList<ArrayList<Object>>();
        ArrayList<Object> temp = new ArrayList<Object>();
        temp.add("month"); // x axis
        temp.add("spending"); //y axis
        chartData.add(temp); //add to chartData

        //Initialize a Treemap to pool all transactions for each month into one value
        //I use a Treemap because it automatically sorts my keys, i.e. the months
        TreeMap<Month, Integer> map = new TreeMap<Month, Integer>();

        //Get all transactions
        List<Transaction> transactions = transactionRepository.findAllByUser(user);


        //Add each transaction to the empty list as a pair of month and amount
        for (Transaction t : transactions){
            Month m = t.getDate().getMonth();
            int i = t.getAmount();

            if(map.containsKey(m)){
                map.put(m, map.get(m)+i);
            }
            else{
                map.put(m, i);
            }
        }

        Iterator<Map.Entry<Month, Integer>> entrySet = map.entrySet().iterator();

        while(entrySet.hasNext()){
            Map.Entry<Month, Integer> entry = entrySet.next();
            temp = new ArrayList<Object>();
            temp.add(entry.getKey());
            temp.add(entry.getValue());
            chartData.add(temp);
        }
        return chartData;
    }
}
