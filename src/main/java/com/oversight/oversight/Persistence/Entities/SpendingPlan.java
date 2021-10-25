package com.oversight.oversight.Persistence.Entities;

import javax.persistence.*;

@Entity
public class SpendingPlan {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long ID;

    @OneToOne(fetch = FetchType.LAZY)
    private User user;



    public SpendingPlan() {
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
