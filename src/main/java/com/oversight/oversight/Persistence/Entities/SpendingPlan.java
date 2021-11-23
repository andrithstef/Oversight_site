package com.oversight.oversight.Persistence.Entities;

import javax.persistence.*;
import java.util.ArrayList;

@Entity
@Table(name="spendingplan")
public class SpendingPlan {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long ID;

    private float food;
    private float car;
    private float booze;
    private float electronics;
    
    @OneToOne
    private User user;

    public SpendingPlan() {
    }

    public SpendingPlan(User user, float food, float car, float booze, float electronics) {
        this.user = user;
        this.food = food;
        this.car = car;
        this.booze = booze;
        this.electronics = electronics;

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

    public float getFood() {
        return food;
    }

    public void setFood(float food) {
        this.food = food;
    }

    public float getCar() {
        return car;
    }

    public void setCar(float car) {
        this.car = car;
    }

    public float getBooze() {
        return booze;
    }

    public void setBooze(float booze) {
        this.booze = booze;
    }

    public float getElectronics() {
        return electronics;
    }

    public void setElectronics(float electronics) {
        this.electronics = electronics;
    }
}
