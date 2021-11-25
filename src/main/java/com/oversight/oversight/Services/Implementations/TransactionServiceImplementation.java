package com.oversight.oversight.Services.Implementations;

import com.oversight.oversight.Persistence.Entities.SpendingPlan;
import com.oversight.oversight.Persistence.Entities.Transaction;
import com.oversight.oversight.Persistence.Entities.User;
import com.oversight.oversight.Persistence.Repositories.TransactionRepository;
import com.oversight.oversight.Services.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
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
    public Transaction save(Transaction transaction) {
        return transactionRepository.save(transaction);
    }

    @Override
    public void delete(Transaction transaction) {
        transactionRepository.delete(transaction);
    }

    @Override
    public List<Transaction> findAllByUser(User user) {
        //get all transactions
        List<Transaction> all = transactionRepository.findAllByUser(user);


        //put them in a tree, this sorts them
        //Here we sort by date
        TreeMap<LocalDate, ArrayList<Transaction>> tree = new TreeMap<LocalDate, ArrayList<Transaction>>();

        ArrayList<Transaction> temp = new ArrayList<Transaction>();

        for(Transaction t: all){
            LocalDate date = t.getDate();
            if(tree.containsKey(date)){
                temp = tree.get(date);
                temp.add(t);
                tree.put(date, temp);
            }
            else{
                temp = new ArrayList<Transaction>();
                temp.add(t);
                tree.put(date, temp);
            }
        }

        //Get all from tree and put into list
        Iterator<Map.Entry<LocalDate, ArrayList<Transaction>>> entrySet = tree.entrySet().iterator();

        List<Transaction> allTransactions = new ArrayList<Transaction>();

        while(entrySet.hasNext()){
            Map.Entry<LocalDate, ArrayList<Transaction>> entry = entrySet.next();
            temp = entry.getValue();
            for(Transaction t: temp){
                allTransactions.add(0,t);
            }
        }

        return allTransactions;
    }

    @Override
    public List<Transaction> findAllByUserByCategory(User user) {
        //The same as before but here we sort by category
        List<Transaction> all = transactionRepository.findAllByUser(user);

        TreeMap<String, ArrayList<Transaction>> tree = new TreeMap<String, ArrayList<Transaction>>();

        ArrayList<Transaction> temp = new ArrayList<Transaction>();

        for(Transaction t: all){
            String s = t.getCategory().getDisplayName();
            if(tree.containsKey(s)){
                temp = tree.get(s);
                temp.add(t);
                tree.put(s, temp);
            }
            else{
                temp = new ArrayList<Transaction>();
                temp.add(t);
                tree.put(s, temp);
            }
        }

        Iterator<Map.Entry<String, ArrayList<Transaction>>> entrySet = tree.entrySet().iterator();

        List<Transaction> allTransactions = new ArrayList<Transaction>();

        while(entrySet.hasNext()){
            Map.Entry<String, ArrayList<Transaction>> entry = entrySet.next();
            temp = entry.getValue();
            for(Transaction t: temp){
                allTransactions.add(t);
            }
        }

        return allTransactions;
    }

    @Override
    public List<Transaction> findAllByUserByAmount(User user) {
        //Same but sort by amount
        List<Transaction> all = transactionRepository.findAllByUser(user);

        TreeMap<Integer, ArrayList<Transaction>> tree = new TreeMap<Integer, ArrayList<Transaction>>();

        ArrayList<Transaction> temp = new ArrayList<Transaction>();

        for(Transaction t: all){
            int i = t.getAmount();
            if(tree.containsKey(i)){
                temp = tree.get(i);
                temp.add(t);
                tree.put(i, temp);
            }
            else{
                temp = new ArrayList<Transaction>();
                temp.add(t);
                tree.put(i, temp);
            }
        }

        Iterator<Map.Entry<Integer, ArrayList<Transaction>>> entrySet = tree.entrySet().iterator();

        List<Transaction> allTransactions = new ArrayList<Transaction>();

        while(entrySet.hasNext()){
            Map.Entry<Integer, ArrayList<Transaction>> entry = entrySet.next();
            temp = entry.getValue();
            for(Transaction t: temp){
                allTransactions.add(0,t);
            }
        }

        return allTransactions;
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
            String s = t.getCategory().getDisplayName();
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

        //This is the current year
        int year = LocalDate.now().getYear();

        //Initialize a Treemap to pool all transactions for each month into one value
        //I use a Treemap because it automatically sorts my keys, i.e. the months
        TreeMap<Month, Integer> map = new TreeMap<Month, Integer>();

        //Get all transactions
        List<Transaction> transactions = transactionRepository.findAllByUser(user);

        //Add each transaction to the empty list as a pair of month and amount
        for (Transaction t : transactions){
            Month m = t.getDate().getMonth();
            if(t.getDate().getYear() == year){
                int i = t.getAmount();

                if(map.containsKey(m)){
                    map.put(m, map.get(m)+i);
                }
                else{
                    map.put(m, i);
                }
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

    @Override
    public ArrayList<ArrayList<Object>> getLineChartDataPlan(User user, SpendingPlan sp) {
        //Initialize the data, by month and spending
        ArrayList<ArrayList<Object>> chartData = new ArrayList<ArrayList<Object>>();
        ArrayList<Object> temp = new ArrayList<Object>();
        temp.add("month"); // x axis
        temp.add("spending"); //y axis
        temp.add("spendingPlan"); //y axis
        chartData.add(temp); //add to chartData

        //This is the current year
        int year = LocalDate.now().getYear();

        //Initialize a Treemap to pool all transactions for each month into one value
        //I use a Treemap because it automatically sorts my keys, i.e. the months
        TreeMap<Month, Integer> map = new TreeMap<Month, Integer>();

        //Get all transactions
        List<Transaction> transactions = transactionRepository.findAllByUser(user);

        //Add each transaction to the empty list as a pair of month and amount
        for (Transaction t : transactions){
            Month m = t.getDate().getMonth();
            if(t.getDate().getYear() == year){
                int i = t.getAmount();

                if(map.containsKey(m)){
                    map.put(m, map.get(m)+i);
                }
                else{
                    map.put(m, i);
                }
            }
        }

        int plan = sp.getTotal();

        Iterator<Map.Entry<Month, Integer>> entrySet = map.entrySet().iterator();

        while(entrySet.hasNext()){
            Map.Entry<Month, Integer> entry = entrySet.next();
            temp = new ArrayList<Object>();
            temp.add(entry.getKey());
            temp.add(entry.getValue());
            temp.add(plan);
            chartData.add(temp);
        }
        return chartData;
    }


    @Override
    public Transaction findByID(long ID) {
        return transactionRepository.findByID(ID);
    }
}
