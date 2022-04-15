package com.oversight.oversight.Services;

import com.oversight.oversight.Persistence.Entities.BankAccount;
import com.oversight.oversight.Persistence.Entities.User;

public interface BankService {

    BankAccount addFunds(int added, BankAccount b);

    BankAccount removeFunds(int removed, BankAccount b);

    BankAccount findByUser(User user);

    BankAccount finddByID(long id);
}
