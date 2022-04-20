package com.oversight.oversight.Controllers;

import com.oversight.oversight.Persistence.Entities.*;
import com.oversight.oversight.Services.BankService;
import com.oversight.oversight.Services.SpendingPlanService;
import com.oversight.oversight.Services.TransactionService;
import com.oversight.oversight.Services.UserService;
import net.bytebuddy.description.method.MethodDescription;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.util.Pair;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.lang.reflect.Type;
import java.time.LocalDate;
import java.util.Base64;
import java.util.HashMap;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

@RestController
public class UserRestController {
    private UserService userService;
    private TransactionService transactionService;
    private SpendingPlanService spendingPlanService;
    private BankService bs;
    private static final Base64.Decoder base64Decoder = Base64.getDecoder(); // for decoding server calls

    @Autowired
    public UserRestController(UserService userService, TransactionService transactionService, SpendingPlanService spendingPlanService, BankService bs){
        this.userService = userService;
        this.transactionService = transactionService;
        this.spendingPlanService = spendingPlanService;
        this.bs = bs;
    }


    @RequestMapping("/loginUser")
    public AppUser loginApp(@RequestBody String data){
        HashMap<String, String> map = TransactionRestController.createMap(data);
        String userName = map.get("userName");
        String password = map.get("password");

        User exists = userService.findByUsername(userName);
        if (exists != null){
            if (password.equals(exists.getPassword())){
                return exists.getAppUser();
            }
            else{
                AppUser a = exists.getAppUser();
                a.setPassword("wrong password");
                return a;
            }
        }
        return null;
    }

    @RequestMapping("/createAppUser")
    public AppUser createAppUser(@RequestBody String data){
        HashMap<String,String> map = TransactionRestController.createMap(data);
        String userName = map.get("userName");
        String password = map.get("password");

        User exists = userService.findByUsername(userName);
        if (exists == null){
            User user = new User(userName, password);
            user.setCreated(LocalDate.now());
            exists = userService.save(user);
            List<Transaction> t = transactionService.generateTransactions(exists);
            SpendingPlan sp = spendingPlanService.createSpendingPlan(exists);
            BankAccount bc = bs.createBankAccount(exists);
            exists.setSpendingPlan(sp);
            exists.setTransactions(t);
            exists.setBankAccount(bc);
            t = t.stream().filter(new Predicate<Transaction>() {
                @Override
                public boolean test(Transaction transaction) {
                    return (transaction.getAmount()>=0) && (transaction.getCategory()!= null);
                }
            }).collect(Collectors.toList());
            exists.setAmountOfTransactions(t.size());
            System.out.println(exists.getAppUser());
            return exists.getAppUser();
        }
        return null;
    }

    @RequestMapping("/deleteAppUser")
    public boolean deleteAppUser(@RequestBody String data){
        HashMap<String, String> map = TransactionRestController.createMap(data);
        String userName = map.get("userName");
        String password = map.get("password");
        String confirmPassword = map.get("confirmPassword");

        User exists = userService.findByUsername(userName);
        if (exists != null){
            if (password.equals(exists.getPassword()) && confirmPassword.equals(exists.getPassword())){
                userService.delete(exists);
                return true;
            }
        }
        return false;
    }

    @RequestMapping("/changeAppPassword")
    public AppUser changePassword(@RequestBody String data){
        HashMap<String, String> map = TransactionRestController.createMap(data);
        String userName = map.get("userName");
        String oldPassword = map.get("oldPassword");
        String newPassword = map.get("newPassword");

        User exists = userService.findByUsername(userName);
        if (exists != null){
            if (oldPassword.equals(exists.getPassword())){
                exists = userService.changePassword(exists, newPassword);
                return exists.getAppUser();
            }
        }
        return null;
    }

    @RequestMapping(value = "/testPost", method = RequestMethod.POST)
    public String testPost(@RequestBody String data){
        System.out.println(data);
        User user = User.createUser(data);
        System.out.println(user);
        return data;
    }
}
