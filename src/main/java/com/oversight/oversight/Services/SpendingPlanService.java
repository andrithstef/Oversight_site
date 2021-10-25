package com.oversight.oversight.Services;

import com.oversight.oversight.Persistence.Entities.SpendingPlan;

import java.util.List;

public interface SpendingPlanService {
    SpendingPlan save(SpendingPlan spendingPlan);
    //SpendingPlan reset(SpendingPlan spendingPlan);
    //List<Float> getAllCategories();
    //void changeCategory(String category, float fraction);
    //float getCategoryPercentage(String category);
}
