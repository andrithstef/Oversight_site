package com.oversight.oversight.Services;

import com.oversight.oversight.Persistence.Entities.SpendingPlan;
import com.oversight.oversight.Persistence.Entities.User;

import java.util.ArrayList;
import java.util.List;

public interface SpendingPlanService {
    SpendingPlan save(SpendingPlan spendingPlan);
    void delete(SpendingPlan spendingPlan);
    List<SpendingPlan> findAll();
    SpendingPlan findByID(long id);
    SpendingPlan findByUser(User user);
    ArrayList<ArrayList<Object>> getPieChartData(User user);
    //void changeCategory(String category, float fraction);
    //float getCategoryPercentage(String category);
    // SpendingPlan reset(SpendingPlan spendingPlan);
}
