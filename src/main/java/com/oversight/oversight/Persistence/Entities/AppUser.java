package com.oversight.oversight.Persistence.Entities;

//import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class AppUser {

    private String userName;

    private String password;
    private long ID;

    private List<AppTransaction> transactionList;
    private HashMap<Category, Integer> sp;

    public AppUser(User user){
        this.userName = user.getUsername();
        this.password = user.getPassword();
        this.ID = user.getID();

        this.transactionList = refactorTransactions(user.getTransactions());

        this.sp = user.getSpendingPlan().getMap();
    }

    public String toString(){
        int l = transactionList.size();
        return transactionList.toString() + l;
    }

    public AppUser(){}

    public static List<AppTransaction> refactorTransactions(List<Transaction> transactions){
        ArrayList<AppTransaction> t = new ArrayList<>();
        for (Transaction transaction : transactions){
            System.out.println(transaction);
            t.add(new AppTransaction(transaction));
        }
        return t;
    }

    public List<AppTransaction> getTransactionList() {
        return transactionList;
    }

    public void setTransactionList(List<AppTransaction> transactionList) {
        this.transactionList = transactionList;
    }

    public HashMap<Category, Integer> getSp() {
        return sp;
    }

    public void setSp(HashMap<Category, Integer> sp) {
        this.sp = sp;
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
