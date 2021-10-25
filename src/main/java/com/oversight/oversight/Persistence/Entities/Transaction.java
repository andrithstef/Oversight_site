package com.oversight.oversight.Persistence.Entities;

import javax.persistence.*;

@Entity
@Table(name="transactions")
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long ID;

    private int amount;

    @ManyToOne
    private User user;

    private String category;

    public Transaction() {
    }

    public Transaction(int amount, User user, String category) {
        this.amount = amount;
        this.user = user;
        this.category = category;
    }

    public long getID() {
        return ID;
    }

    public void setID(long ID) {
        this.ID = ID;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }
}
