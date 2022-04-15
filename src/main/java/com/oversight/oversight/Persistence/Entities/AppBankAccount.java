package com.oversight.oversight.Persistence.Entities;

public class AppBankAccount {

    private long ID;
    private int balance;

    public AppBankAccount(BankAccount b){
        this.ID = b.getID();
        this.balance = b.getBalance();
    }

    public long getID() {
        return ID;
    }

    public void setID(long ID) {
        this.ID = ID;
    }

    public int getBalance() {
        return balance;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }
}
