package com.oversight.oversight.Controllers;

import com.oversight.oversight.Persistence.Entities.*;
import com.oversight.oversight.Services.BankService;
import com.oversight.oversight.Services.SpendingPlanService;
import com.oversight.oversight.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

@RestController
public class BankRestController {
    private BankService bankService;
    private UserService userService;

    @Autowired
    public BankRestController(UserService userService, BankService bankService) {
        this.userService = userService;
        this.bankService = bankService;
    }


    @RequestMapping("/getBankAccount")
    public AppBankAccount getBankAccount(@RequestBody String data){
        System.out.println("Fetching bank account");
        HashMap<String, String> map = TransactionRestController.createMap(data);
        String username = map.get("userName");

        User user = userService.findByUsername(username);
        BankAccount b = bankService.findByUser(user);

        if (b == null){
            System.out.println("No bank account exists, creating...");
            b = new BankAccount();
            b.setBalance(0);
            b.setUser(user);
            b = bankService.addFunds(0, b);
        }

        System.out.println("balance : " + b.getBalance());
        return new AppBankAccount(b);
    }

    @RequestMapping("addFunds")
    public AppBankAccount addFunds(@RequestBody String data){
        try{
            HashMap<String, String> map = TransactionRestController.createMap(data);
            String username = map.get("userName");
            String added = map.get("added");
            int v = Integer.parseInt(added);

            User user = userService.findByUsername(username);
            BankAccount b = bankService.findByUser(user);
            b = bankService.addFunds(v, b);

            return new AppBankAccount(b);
        }catch (Exception e){
            return null;
        }
    }

    @RequestMapping("removeFunds")
    public AppBankAccount removeFunds(@RequestBody String data){
        try{
            HashMap<String, String> map = TransactionRestController.createMap(data);
            String username = map.get("userName");
            String removed = map.get("removed");
            int v = Integer.parseInt(removed);

            User user = userService.findByUsername(username);
            BankAccount b = bankService.findByUser(user);
            b = bankService.removeFunds(v, b);

            return new AppBankAccount(b);
        }catch (Exception e){
            return null;
        }
    }
}
