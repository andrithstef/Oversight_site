package com.oversight.oversight.Persistence.Entities;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.TreeMap;

@Entity
@Table(name="spendingplan")
public class SpendingPlan {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long ID;

    @ElementCollection
    private TreeMap<Category, Integer> catMap;

    @OneToOne
    private User user;

    public SpendingPlan() {
    }

    public SpendingPlan(User user, ArrayList<Transaction> cats) {
        this.user = user;
        TreeMap<Category, Integer> catMap = new TreeMap<Category, Integer>();
        for(Transaction t : cats){
            catMap.put(t.getCategory(), t.getAmount());
        }
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

    public TreeMap<Category, Integer> getCatMap() {
        return catMap;
    }

    public void setCatMap(TreeMap<Category, Integer> catMap) {
        this.catMap = catMap;
    }
}
