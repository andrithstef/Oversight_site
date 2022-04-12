package com.oversight.oversight.Services;

import com.oversight.oversight.Persistence.Entities.SpendingPlan;
import com.oversight.oversight.Persistence.Entities.User;

import java.util.ArrayList;

public interface SpendingPlanService {
    SpendingPlan save(SpendingPlan spendingPlan);
    void delete(SpendingPlan spendingPlan);

    SpendingPlan findByUser(User user);

    SpendingPlan createSpendingPlan(User user);

    ArrayList<ArrayList<Object>> getPieChartData(User user);

    SpendingPlan findByID(long id);
}
