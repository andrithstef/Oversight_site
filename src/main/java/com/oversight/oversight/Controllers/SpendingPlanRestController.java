package com.oversight.oversight.Controllers;

import com.oversight.oversight.Persistence.Entities.AppSpendingPlan;
import com.oversight.oversight.Persistence.Entities.Category;
import com.oversight.oversight.Persistence.Entities.SpendingPlan;
import com.oversight.oversight.Persistence.Entities.User;
import com.oversight.oversight.Services.SpendingPlanService;
import com.oversight.oversight.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.persistence.criteria.CriteriaBuilder;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

@RestController
public class SpendingPlanRestController {
    private SpendingPlanService spendingPlanService;
    private UserService userService;

    @Autowired
    public SpendingPlanRestController(SpendingPlanService spendingPlanService, UserService userService) {
        this.spendingPlanService = spendingPlanService;
        this.userService = userService;
    }


    @RequestMapping("/createAppSpendingPlan")
    public AppSpendingPlan createSpendingPlan(@RequestBody String data){
        HashMap<String, String> map = TransactionRestController.createMap(data);
        String username = map.get("userName");
        map.remove("userName");

        User user = userService.findByUsername(username);
        HashMap<Category, Integer> spendingPlanMap = new HashMap<>();

        Iterator m = map.entrySet().iterator();

        while (m.hasNext()){
            Map.Entry entry = (Map.Entry)m.next();
            try{
                String cat = (String) entry.getKey();
                Category c = Category.valueOf(cat);
                String val = (String) entry.getValue();
                int v = Integer.parseInt(val);
                spendingPlanMap.put(c, v);
            }catch (Exception e){
                System.out.println(e);
            }
        }
        SpendingPlan toDelete = spendingPlanService.findByUser(user);
        spendingPlanService.delete(toDelete);

        SpendingPlan sp = SpendingPlan.createFromMap(spendingPlanMap, user);
        sp = spendingPlanService.save(sp);
        return new AppSpendingPlan(sp);
    }

    @RequestMapping("/getSpendingPlan")
    public AppSpendingPlan getSpendingPlan(@RequestBody String data){
        System.out.println(data);
        HashMap<String, String> map = TransactionRestController.createMap(data);
        String username = map.get("userName");
        User user = userService.findByUsername(username);
        SpendingPlan sp = spendingPlanService.findByUser(user);

        return new AppSpendingPlan(sp);
    }

    @RequestMapping("/deleteSpendingPlan")
    public Boolean deleteSpendingPlan(@RequestBody String data){
        try{
            HashMap<String, String> map = TransactionRestController.createMap(data);
            String username = map.get("userName");
            User user = userService.findByUsername(username);
            SpendingPlan sp = spendingPlanService.findByUser(user);
            spendingPlanService.delete(sp);
            return true;
        }catch (Exception e){
            return false;
        }

    }



}
