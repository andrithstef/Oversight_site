package com.oversight.oversight.Persistence.Repositories;

import com.oversight.oversight.Persistence.Entities.SpendingPlan;
import com.oversight.oversight.Persistence.Entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SpendingPlanRepository extends JpaRepository<SpendingPlan, Long> {
    SpendingPlan save(SpendingPlan spendingPlan);
    //SpendingPlan reset(SpendingPlan spendingPlan);
    void delete(SpendingPlan spendingPlan);
    List<SpendingPlan> findAll();
    SpendingPlan findByID(long id);
    SpendingPlan findByUser(User user);
    //void changeCategory(String category, float fraction);
    //float getCategoryPercentage(String category);

}
