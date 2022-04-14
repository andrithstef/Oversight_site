package com.oversight.oversight.Controllers;

import com.oversight.oversight.Persistence.Entities.*;
import com.oversight.oversight.Services.TransactionService;
import com.oversight.oversight.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import java.time.LocalDate;
import java.util.*;

import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

@RestController
public class TransactionRestController {

    private TransactionService transactionService;
    private UserService userService;

    @Autowired
    public TransactionRestController(TransactionService transactionService, UserService userService){
        this.transactionService = transactionService;
        this.userService = userService;


    }

    @RequestMapping("/deleteTransaction/{id}")
    public boolean deleteTransaction(@PathVariable(value = "id") String id){
        long transactionID = Long.getLong(id);
        Transaction t = transactionService.findByID(transactionID);
        if (t != null){
            transactionService.delete(t);
            return true;
        }
        return false;
    }

    @RequestMapping("/getTransactions")
    public List<AppTransaction> getTransactions(@RequestBody String data){
        User user = User.createUser(data);
        User exists = userService.findByUsername(user.getUsername());
        return exists.getAppUser().getTransactionList();
    }

    @RequestMapping("/createTransaction")
    public List<AppTransaction> createTransaction(@RequestBody String data){
        System.out.println(data);
        HashMap<String, String > map = createMap(data);
        System.out.println(map);
        System.out.println("############################");
        try{
            System.out.println("##################################");
            User user = userService.findByUsername(map.get("userName"));
            Transaction t = new Transaction();
            t.setAmount(Integer.parseInt(map.get("amount")));
            t.setDate(LocalDate.parse(map.get("date")));
            t.setCategory(Category.valueOf(map.get("category")));
            t.setUser(user);
            System.out.println(t);
            transactionService.save(t);
            List<Transaction> transactions = transactionService.findAllByUser(user);
            ArrayList<AppTransaction> at = new ArrayList<>();
            for (Transaction transaction : transactions){
                at.add(new AppTransaction(transaction));
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
