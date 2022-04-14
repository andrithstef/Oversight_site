package com.oversight.oversight.Controllers;

import com.oversight.oversight.Persistence.Entities.Category;
import com.oversight.oversight.Persistence.Entities.SpendingPlan;
import com.oversight.oversight.Persistence.Entities.User;
import com.oversight.oversight.Services.SpendingPlanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.HashMap;

@RestController
public class SpendingPlanRestController {
    private SpendingPlanService spendingPlanService;

    @Autowired
    public SpendingPlanRestController(SpendingPlanService spendingPlanService) {
        this.spendingPlanService = spendingPlanService;
    }

    @RequestMapping("/getSpendingPlan/{userId}")
    public HashMap<Category, Integer> getSpendingPlan(HttpSession session){



        //Get the logged in user's spending plan
        User loggedIn = (User) session.getAttribute("LoggedInUser");
        SpendingPlan spendingPlan = spendingPlanService.findByUser(loggedIn);

        return spendingPlan.getMap();
    }


}
