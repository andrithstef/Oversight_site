package com.oversight.oversight.Persistence.Entities;

import java.time.LocalDate;

public class AppTransaction {
    private int amount;
    private Category category;
    private LocalDate date;

    public AppTransaction(Transaction t){
        this.amount = t.getAmount();
        this.category = t.getCategory();
        this.date = t.getDate();
        System.out.println(amount + " " + category);
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }
}