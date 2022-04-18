package com.oversight.oversight.Controllers;

import com.google.gson.Gson;
import com.oversight.oversight.Persistence.Entities.AppUser;
import com.oversight.oversight.Persistence.Entities.SpendingPlan;
import com.oversight.oversight.Persistence.Entities.Transaction;
import com.oversight.oversight.Persistence.Entities.User;
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
import java.util.Base64;
import java.util.HashMap;
import java.util.List;

@RestController
public class UserRestController {
    private UserService userService;
    private TransactionService transactionService;
    private SpendingPlanService spendingPlanService;
    private static final Base64.Decoder base64Decoder = Base64.getDecoder(); // for decoding server calls

    @Autowired
    public UserRestController(UserService userService, TransactionService transactionService, SpendingPlanService spendingPlanService){
        this.userService = userService;
        this.transactionService = transactionService;
        this.spendingPlanService = spendingPlanService;
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
        String generate = map.get("generate");

        User exists = userService.findByUsername(userName);
        if (exists == null){
            User user = new User(userName, password);
            exists = userService.save(user);
            List<Transaction> t = transactionService.generateTransactions(exists, generate);
            SpendingPlan sp = spendingPlanService.createSpendingPlan(exists, generate);
            exists.setSpendingPlan(sp);
            exists.setTransactions(t);
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




    private Pair<String, String> myDecoder(String userToken) {
        String decodedToken = new String(base64Decoder.decode(userToken));
        System.out.println("Decoded string is: " + decodedToken);
        int i;
        for (i = 0; i < decodedToken.length(); i++) {
            if (decodedToken.charAt(i) == '%') {
                break;
            }
        }
        // We now have the index of the end of the email and the start of the password
        String email = decodedToken.substring(0,i);
        String password = decodedToken.substring(i+1);
        System.out.println("here is the decoded email: " + email);
        System.out.println("here is the decoded password: " + password);
        return Pair.of(email, password);
    }

}
