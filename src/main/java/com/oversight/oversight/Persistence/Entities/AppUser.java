package com.oversight.oversight.Persistence.Entities;

//import com.google.gson.annotations.SerializedName;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class AppUser {

    private String userName;

    private String password;
    private long ID;
    private LocalDate created;


    private int amountOfTransactions;

    public AppUser(User user){
        this.userName = user.getUsername();
        this.password = user.getPassword();
        this.ID = user.getID();
        this.created = user.getCreated();
        this.amountOfTransactions = user.getAmountOfTransactions();
    }


    public AppUser(){}

    public int getAmountOfTransactions() {
        return amountOfTransactions;
    }

    public void setAmountOfTransactions(int amountOfTransactions) {
        this.amountOfTransactions = amountOfTransactions;
    }

    public static List<AppTransaction> refactorTransactions(List<Transaction> transactions){
        ArrayList<AppTransaction> t = new ArrayList<>();
        for (Transaction transaction : transactions){
            System.out.println(transaction);
            t.add(new AppTransaction(transaction));
        }
        return t;
    }

    public LocalDate getCreated() {
        return created;
    }

    public void setCreated(LocalDate created) {
        this.created = created;
    }

    public String toString(){
        return userName + " / " + amountOfTransactions + " / " + created;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public long getID() {
        return ID;
    }

    public void setID(long ID) {
        this.ID = ID;
    }
}
