package com.oversight.oversight.Controllers;

import com.oversight.oversight.Services.SpendingPlanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
public class SpendingPlanController {

    private SpendingPlanService spendingPlanService;

    @Autowired
    public SpendingPlanController(SpendingPlanService spendingPlanService) {
        this.spendingPlanService = spendingPlanService;
    }

}
