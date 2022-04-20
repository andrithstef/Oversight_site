package com.oversight.oversight.Persistence.Entities;

import javax.persistence.*;

@Entity
@Table(name="bankAccount")
public class BankAccount {

    @Id
    @GeneratedValue
    private long ID;

    @OneToOne
    private User user;

    private int balance;


    public BankAccount() {
    }

    public BankAccount(User user, int balance) {
        this.user = user;
        this.balance = balance;
    }

    public long getID() {
        return ID;
    }

    public void setID(long ID) {
        this.ID = ID;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public int getBalance() {
        return balance;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }
}
