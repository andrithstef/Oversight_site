package com.oversight.oversight.Persistence.Repositories;

import com.oversight.oversight.Persistence.Entities.SpendingPlan;
import com.oversight.oversight.Persistence.Entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SpendingPlanRepository extends JpaRepository<SpendingPlan, Long> {
    SpendingPlan save(SpendingPlan spendingPlan);
    void delete(SpendingPlan spendingPlan);

    SpendingPlan findByUser(User user);

    SpendingPlan findByID(long id);
}
