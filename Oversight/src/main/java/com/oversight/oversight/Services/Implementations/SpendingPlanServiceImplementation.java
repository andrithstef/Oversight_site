package com.oversight.oversight.Services.Implementations;

import com.oversight.oversight.Persistence.Entities.SpendingPlan;
import com.oversight.oversight.Persistence.Entities.User;
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

    @Override
    public void delete(SpendingPlan spendingPlan) {
        spendingPlanRepository.delete(spendingPlan);
    }

    @Override
    public List<SpendingPlan> findAll() {
        return spendingPlanRepository.findAll();
    }

    @Override
    public SpendingPlan findByID(long id) {
        return spendingPlanRepository.findByID(id);
    }

    @Override
    public SpendingPlan findByUser(User user) {
        return spendingPlanRepository.findByUser(user);
    }
    /*
    @Override
    public void changeCategory(String category, float fraction) {

    }

    @Override
    public float getCategoryPercentage(String category) {
        return 0;
    }
     */
}
