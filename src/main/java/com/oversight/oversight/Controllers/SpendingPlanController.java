package com.oversight.oversight.Controllers;

import com.oversight.oversight.Persistence.Entities.SpendingPlan;
import com.oversight.oversight.Persistence.Entities.Transaction;
import com.oversight.oversight.Persistence.Entities.User;
import com.oversight.oversight.Services.SpendingPlanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class SpendingPlanController {
    private SpendingPlanService spendingPlanService;

    @Autowired
    public SpendingPlanController(SpendingPlanService spendingPlanService) {
        this.spendingPlanService = spendingPlanService;
    }

    @RequestMapping("/seeSpendingPlan")
    public String homePage(Model model, HttpSession session){

        User loggedIn = (User) session.getAttribute("LoggedInUser");
        SpendingPlan spendingPlan = spendingPlanService.findByUser(loggedIn);

        // Add data to the model
        model.addAttribute("seespendingplan", spendingPlan);
        return "seeSpendingPlan";
    }

    @RequestMapping(value = "/createSpendingPlan", method = RequestMethod.GET)
    public String createSpendingPlanGET(Model model){
        model.addAttribute("spendingplan", new SpendingPlan());
        //fara yfir á síðuna createUser
        return "newSpendingPlan";
    }

    @RequestMapping(value = "/createSpendingPlan", method = RequestMethod.POST)
    public String createSpendingPlanPOST(@ModelAttribute("spendingplan") SpendingPlan spendingPlan, BindingResult result, HttpSession session){
        if (result.hasErrors()){
            return "newSpendingPlan";
        }
        User loggedIn = (User) session.getAttribute("LoggedInUser");
        SpendingPlan spendingPlanExists = spendingPlanService.findByUser(loggedIn);
        if (spendingPlanExists == null) {
            spendingPlan.setUser(loggedIn);
            spendingPlanService.save(spendingPlan);
            return "redirect:/seeSpendingPlan";
        }
        return "spendingPlanAlreadyExists";
    }

    @RequestMapping(value="/deletespendingplan/{id}", method = RequestMethod.GET)
    public String deleteSpendingPlan(@PathVariable("id") long id, Model model){
        SpendingPlan spendingPlanToDelete = spendingPlanService.findByID(id);
        spendingPlanService.delete(spendingPlanToDelete);
        return "redirect:/seeSpendingPlan";
    }
}
