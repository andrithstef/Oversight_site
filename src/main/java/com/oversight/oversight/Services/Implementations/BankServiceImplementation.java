package com.oversight.oversight.Services.Implementations;

import com.oversight.oversight.Persistence.Entities.BankAccount;
import com.oversight.oversight.Persistence.Entities.User;
import com.oversight.oversight.Persistence.Repositories.BankRepository;
import com.oversight.oversight.Services.BankService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BankServiceImplementation implements BankService {

    private BankRepository br;

    @Autowired
    public BankServiceImplementation(BankRepository br){
        this.br = br;
    }

    @Override
    public BankAccount addFunds(int added, BankAccount b) {
        int balanace = b.getBalance() + added;
        b.setBalance(balanace);
        return br.save(b);
    }

    @Override
    public BankAccount removeFunds(int removed, BankAccount b) {
        int balance = b.getBalance() - removed;
        b.setBalance(balance);
        return br.save(b);
    }

    @Override
    public BankAccount findByUser(User user) {
        return br.findByUser(user);
    }

    @Override
    public BankAccount finddByID(long id) {
        return br.findByID(id);
    }

    @Override
    public BankAccount save(BankAccount b) {
        return br.save(b);
    }

    @Override
    public BankAccount createBankAccount(User user) {
        BankAccount b = new BankAccount();
        b.setUser(user);
        int balance = (int)(90000-Math.random()*70000);
        b.setBalance(balance);
        return br.save(b);
    }
}
