package com.oversight.oversight.Persistence.Entities;

import com.google.gson.annotations.SerializedName;
import com.oversight.oversight.Controllers.TransactionRestController;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.StringTokenizer;

@Entity
@Table(name="users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long ID;


    private String username;
    private String password;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = false)
    private List<Transaction> transactions = new ArrayList<>();

    @OneToOne(mappedBy = "user", orphanRemoval = true)
    private SpendingPlan spendingPlan;

    public User() {
    }

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String toString(){
        return this.username + " // " + this.password;
    }

    public static User createUser(String string){
        HashMap<String ,String> map = TransactionRestController.createMap(string);
        String userName = map.get("userName");
        String password = map.get("password");
        User user = new User();
        user.setPassword(password);
        user.setUsername(userName);
        return user;
    }

    public AppUser getAppUser(){
        return new AppUser(this);
    }

    public long getID() {
        return ID;
    }

    public void setID(long ID) {
        this.ID = ID;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<Transaction> getTransactions() {
        return transactions;
    }

    public void setTransactions(List<Transaction> transactions) {
        this.transactions = transactions;
    }

    public SpendingPlan getSpendingPlan() {
        return spendingPlan;
    }

    public void setSpendingPlan(SpendingPlan spendingPlan) {
        this.spendingPlan = spendingPlan;
    }
}
