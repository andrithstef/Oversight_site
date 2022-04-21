package com.oversight.oversight.Controllers;

import com.oversight.oversight.Persistence.Entities.*;
import com.oversight.oversight.Services.BankService;
import com.oversight.oversight.Services.TransactionService;
import com.oversight.oversight.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import java.time.LocalDate;
import java.time.Year;
import java.time.YearMonth;
import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

@RestController
public class TransactionRestController {

    private TransactionService transactionService;
    private UserService userService;
    private BankService bankService;

    @Autowired
    public TransactionRestController(TransactionService transactionService, UserService userService, BankService bankService){
        this.transactionService = transactionService;
        this.userService = userService;
        this.bankService = bankService;
    }

    @RequestMapping("/deleteTransaction")
    public List<AppTransaction> deleteTransaction(@RequestBody String data){
        System.out.println(data);
        HashMap<String, String> map = createMap(data);
        String id = map.get("id");
        String userName  = map.get("userName");
        String year = map.get("year");
        String month = map.get("month");
        int y = Integer.parseInt(year);
        int m = Integer.parseInt(month);
        YearMonth yearMonth = YearMonth.of(y,m);
        int intId = Integer.parseInt(id);
        long ID = Integer.toUnsignedLong(intId);
        Transaction t = transactionService.findByID(ID);
        if (t != null){
            System.out.println("deleting transaction: " + t );
            transactionService.delete(t);
        }
        User user = userService.findByUsername(userName);
        if (t.getCategory() != null){
            user.setAmountOfTransactions(user.getAmountOfTransactions()-1);
            userService.save(user);
        }

        BankAccount b = bankService.findByUser(user);
        b.setBalance(b.getBalance()+t.getAmount());
        bankService.save(b);
        List<Transaction> transactions = transactionService.findAllByUserByYearMonth(user, yearMonth);
        List<AppTransaction> at = new ArrayList<>();
        for (Transaction transaction : transactions){
            at.add(new AppTransaction(transaction));
        }
        return at;
    }

    @RequestMapping("/getTransactions")
    public List<AppTransaction> getTransactions(@RequestBody String data){
        User user = User.createUser(data);
        User exists = userService.findByUsername(user.getUsername());
        List<Transaction> l = exists.getTransactions();
        l = l.stream().filter(new Predicate<Transaction>() {
            @Override
            public boolean test(Transaction transaction) {
                return (transaction.getAmount()>=0) && (transaction.getCategory()!= null);
            }
        }).collect(Collectors.toList());
        List<AppTransaction> at = new ArrayList<>();
        for (Transaction t: l){
            if (t.getAmount()>=0 && t.getCategory()!= null){
                at.add(new AppTransaction(t));
            }
        }
        return at;
    }

    @RequestMapping("/getTransactionsForDays")
    public List<Integer> getTransactionsForDays(@RequestBody String data){
        HashMap<String, String> map = createMap(data);
        String userName = map.get("userName");
        String password = map.get("password");
        String days = map.get("days");
        User user = userService.findByUsername(userName);

        if (!user.getPassword().equals(password)){
            return null;
        }

        return transactionService.findAllByUserByDate(user, Integer.parseInt(days));
    }


    @RequestMapping("/getTransactionsForMonth")
    public List<AppTransaction> getTransactionsForMonth(@RequestBody String data){
        HashMap<String, String> map = createMap(data);
        String userName = map.get("userName");
        String password = map.get("password");
        String month = map.get("month");
        String year = map.get("year");
        System.out.println(year + " : " + month);
        User user = userService.findByUsername(userName);

        /*
        if (user.getPassword().equals(password)){
            return null;
        }
         */

        int y = Integer.parseInt(year);
        int m = Integer.parseInt(month);
        YearMonth yearMonth = YearMonth.of(y,m);
        List<Transaction> t = transactionService.findAllByUserByYearMonth(user, yearMonth);
        List<AppTransaction> at = new ArrayList<>();
        for (Transaction transaction : t){
            at.add(new AppTransaction(transaction));
        }
        return at;
    }

    @RequestMapping("/createTransaction")
    public List<AppTransaction> createTransaction(@RequestBody String data){
        System.out.println(data);
        HashMap<String, String > map = createMap(data);
        try{
            User user = userService.findByUsername(map.get("userName"));
            Transaction t = new Transaction();
            t.setAmount(Integer.parseInt(map.get("amount")));
            t.setDate(LocalDate.parse(map.get("date")));
            String year = map.get("year");
            String month = map.get("month");
            int y = Integer.parseInt(year);
            int m = Integer.parseInt(month);
            YearMonth yearMonth = YearMonth.of(y,m);
            if (map.containsKey("category")){
                t.setCategory(Category.valueOf(map.get("category")));
                user.setAmountOfTransactions(user.getAmountOfTransactions()+1);
            }
            t.setUser(user);
            userService.save(user);
            System.out.println(t);
            BankAccount b = bankService.findByUser(user);
            System.out.println(b.getBalance());
            b.setBalance(b.getBalance()-t.getAmount());
            System.out.println(b.getBalance());
            bankService.save(b);
            transactionService.save(t);
            List<Transaction> transactions = transactionService.findAllByUserByYearMonth(user, yearMonth);
            ArrayList<AppTransaction> at = new ArrayList<>();
            for (Transaction transaction : transactions){
                if (transaction.getAmount()>=0 && transaction.getCategory() != null){
                    at.add(new AppTransaction(transaction));
                }
            }
            return at;
        }
        catch (Exception e){
            System.out.println(e);
            return null;
        }
    }

    public static HashMap<String, String> createMap(String string) {
        HashMap<String, String> map = new HashMap<>();
        StringTokenizer st = new StringTokenizer(string, "&");
        while (st.hasMoreTokens()) {
            String token = st.nextToken();
            StringTokenizer temp = new StringTokenizer(token, "=");
            map.put(temp.nextToken(), temp.nextToken());
        }
        return map;
    }
}
