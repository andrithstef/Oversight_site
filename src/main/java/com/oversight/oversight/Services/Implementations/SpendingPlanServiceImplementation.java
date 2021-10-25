package com.oversight.oversight.Services.Implementations;

import com.oversight.oversight.Persistence.Entities.SpendingPlan;
import com.oversight.oversight.Persistence.Repositories.SpendingPlanRepository;
import com.oversight.oversight.Services.SpendingPlanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SpendingPlanServiceImplementation implements SpendingPlanService {
    private SpendingPlanRepository spendingPlanRepository;

    @Autowired
    public SpendingPlanServiceImplementation(SpendingPlanRepository spendingPlanRepository) {
        this.spendingPlanRepository = spendingPlanRepository;
    }

    @Override
    public SpendingPlan save(SpendingPlan spendingPlan) {
        return spendingPlanRepository.save(spendingPlan);
    }
    /*
    @Override
    public SpendingPlan reset(SpendingPlan spendingPlan) {
        return null;
    }

    @Override
    public List<Float> getAllCategories() {
        return null;
    }

    @Override
    public void changeCategory(String category, float fraction) {

    }

    @Override
    public float getCategoryPercentage(String category) {
        return 0;
    }
     */
}
