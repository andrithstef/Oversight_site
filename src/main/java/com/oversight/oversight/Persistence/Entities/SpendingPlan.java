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


    @OneToOne
    private User user;

    public SpendingPlan() {
    }

    public SpendingPlan(User user){
        this.user = user;
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
}
