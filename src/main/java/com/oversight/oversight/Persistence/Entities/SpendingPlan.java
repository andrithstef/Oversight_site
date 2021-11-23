package com.oversight.oversight.Persistence.Entities;

import javax.persistence.*;

@Entity
@Table(name="spendingplan")
public class SpendingPlan {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long ID;

    private int cars_transportation;
    private int children;
    private int education;
    private int fines_fees;
    private int food;
    private int health_beauty;
    private int home;
    private int insurance;
    private int investments_savings;
    private int leisure;
    private int shopping_services;
    private int uncategorized;
    private int vacation_travel;

    @OneToOne
    private User user;

    public SpendingPlan() {
    }

    public SpendingPlan(int cars_transportation, int children, int education, int fines_fees, int food,
                        int health_beauty, int home, int insurance, int investments_savings,
                        int leisure, int shopping_services, int uncategorized, int vacation_travel, User user) {
        this.cars_transportation = cars_transportation;
        this.children = children;
        this.education = education;
        this.fines_fees = fines_fees;
        this.food = food;
        this.health_beauty = health_beauty;
        this.home = home;
        this.insurance = insurance;
        this.investments_savings = investments_savings;
        this.leisure = leisure;
        this.shopping_services = shopping_services;
        this.uncategorized = uncategorized;
        this.vacation_travel = vacation_travel;
        this.user = user;
    }

    public long getID() {
        return ID;
    }

    public void setID(long ID) {
        this.ID = ID;
    }

    public int getCars_transportation() {
        return cars_transportation;
    }

    public void setCars_transportation(int cars_transportation) {
        this.cars_transportation = cars_transportation;
    }

    public int getChildren() {
        return children;
    }

    public void setChildren(int children) {
        this.children = children;
    }

    public int getEducation() {
        return education;
    }

    public void setEducation(int education) {
        this.education = education;
    }

    public int getFines_fees() {
        return fines_fees;
    }

    public void setFines_fees(int fines_fees) {
        this.fines_fees = fines_fees;
    }

    public int getFood() {
        return food;
    }

    public void setFood(int food) {
        this.food = food;
    }

    public int getHealth_beauty() {
        return health_beauty;
    }

    public void setHealth_beauty(int health_beauty) {
        this.health_beauty = health_beauty;
    }

    public int getHome() {
        return home;
    }

    public void setHome(int home) {
        this.home = home;
    }

    public int getInsurance() {
        return insurance;
    }

    public void setInsurance(int insurance) {
        this.insurance = insurance;
    }

    public int getInvestments_savings() {
        return investments_savings;
    }

    public void setInvestments_savings(int investments_savings) {
        this.investments_savings = investments_savings;
    }

    public int getLeisure() {
        return leisure;
    }

    public void setLeisure(int leisure) {
        this.leisure = leisure;
    }

    public int getShopping_services() {
        return shopping_services;
    }

    public void setShopping_services(int shopping_services) {
        this.shopping_services = shopping_services;
    }

    public int getUncategorized() {
        return uncategorized;
    }

    public void setUncategorized(int uncategorized) {
        this.uncategorized = uncategorized;
    }

    public int getVacation_travel() {
        return vacation_travel;
    }

    public void setVacation_travel(int vacation_travel) {
        this.vacation_travel = vacation_travel;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
