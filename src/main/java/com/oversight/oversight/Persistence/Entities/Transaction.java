package com.oversight.oversight.Persistence.Entities;

import org.apache.tomcat.jni.Local;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.util.Date;

@Entity
@Table(name="transactions")
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long ID;

    @Min(value = 0, message = "the amount cannot be less than zero")
    private int amount;

    @ManyToOne
    private User user;

    private Category category;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate date;

    public Transaction() {
    }

    public Transaction(int amount, User user, Category category, LocalDate date) {
        this.amount = amount;
        this.user = user;
        this.category = category;
        this.date = date;
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

    public void setDate(LocalDate date){this.date = date;}

    public LocalDate getDate(){return this.date;}

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }
}
