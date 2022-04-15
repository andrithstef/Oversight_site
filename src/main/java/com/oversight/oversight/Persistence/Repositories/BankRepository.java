package com.oversight.oversight.Persistence.Repositories;

import com.oversight.oversight.Persistence.Entities.BankAccount;
import com.oversight.oversight.Persistence.Entities.SpendingPlan;
import com.oversight.oversight.Persistence.Entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BankRepository extends JpaRepository<BankAccount, Long> {


    BankAccount save(BankAccount bankAccount);

    void delete(BankAccount bankAccount);

    BankAccount findByUser(User user);

    BankAccount findByID(long id);
}
