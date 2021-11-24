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
import java.util.ArrayList;
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
        if(spendingPlan != null) {
            model.addAttribute("pieChartData", getPieChartData(loggedIn));
        }
        return "seeSpendingPlan";
    }

    @RequestMapping(value = "/createSpendingPlan", method = RequestMethod.GET)
    public String createSpendingPlanGET(Model model){
        model.addAttribute("spendingplan", new SpendingPlan());
        return "newSpendingPlan";
    }
    //Creates the spending plan
    @RequestMapping(value = "/createSpendingPlan", method = RequestMethod.POST)
    public String createSpendingPlanPOST(@ModelAttribute("spendingplan") SpendingPlan spendingPlan, BindingResult result, HttpSession session){
        //Checks for errors
        if (result.hasErrors()){
            return "newSpendingPlan";
        }
        //Sets the user for the spending plan
        User loggedIn = (User) session.getAttribute("LoggedInUser");
        SpendingPlan spendingPlanExists = spendingPlanService.findByUser(loggedIn);
        //Checks if the spending plan already exists
        if (spendingPlanExists == null) {
            if (spendingPlan.getFood() < 0 || spendingPlan.getChildren() < 0 || spendingPlan.getCars_transportation() < 0 ||
                    spendingPlan.getFines_fees() < 0 || spendingPlan.getEducation() < 0 || spendingPlan.getHealth_beauty() < 0 ||
                    spendingPlan.getHome() < 0 || spendingPlan.getInsurance() < 0 || spendingPlan.getInvestments_savings() < 0 ||
                    spendingPlan.getLeisure() < 0 || spendingPlan.getVacation_travel() < 0 || spendingPlan.getShopping_services() < 0 ||
                    spendingPlan.getUncategorized() < 0) {
                return "spendingPlanNotNegative";
            }
            else {
                spendingPlan.setUser(loggedIn);
                spendingPlanService.save(spendingPlan);
                return "redirect:/seeSpendingPlan";
            }
        }
        return "spendingPlanAlreadyExists";
    }
    //Deletes the spending plan
    @RequestMapping(value="/deleteSpendingPlan/{id}", method = RequestMethod.GET)
    public String deleteSpendingPlan(@PathVariable("id") long id, Model model){
        SpendingPlan spendingPlanToDelete = spendingPlanService.findByID(id);
        spendingPlanService.delete(spendingPlanToDelete);
        return "redirect:/seeSpendingPlan";
    }

    private ArrayList<ArrayList<Object>> getPieChartData(User user){
        return spendingPlanService.getPieChartData(user);
    }
}
