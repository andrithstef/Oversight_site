package com.oversight.oversight.Persistence.Entities;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import java.util.HashMap;

public class AppSpendingPlan {
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


    public AppSpendingPlan() {
    }

    public AppSpendingPlan(SpendingPlan sp) {
        this.cars_transportation = sp.getCars_transportation();
        this.children = sp.getChildren();
        this.education = sp.getEducation();
        this.fines_fees = sp.getFines_fees();
        this.food = sp.getFood();
        this.health_beauty = sp.getHealth_beauty();
        this.home = sp.getHome();
        this.insurance = sp.getInsurance();
        this.investments_savings = sp.getInvestments_savings();
        this.leisure = sp.getLeisure();
        this.shopping_services = sp.getShopping_services();
        this.uncategorized = sp.getUncategorized();
        this.vacation_travel = sp.getVacation_travel();
    }

    public HashMap<Category, Integer> getMap(){
        HashMap<Category,  Integer> map = new HashMap<>();
        map.put(Category.CARS, this.cars_transportation);
        map.put(Category.CHILDREN, this.children);
        map.put(Category.HEALTH, this.health_beauty);
        map.put(Category.FOOD, this.food);
        map.put(Category.FINES, this.fines_fees);
        map.put(Category.EDUCATION, this.education);
        map.put(Category.HOME, this.home);
        map.put(Category.INSURANCE, this.insurance);
        map.put(Category.INVESTMENTS, this.investments_savings);
        map.put(Category.LEISURE, this.leisure);
        map.put(Category.MISC, this.uncategorized);
        map.put(Category.SHOPPING, this.shopping_services);
        map.put(Category.TRAVEL, this.vacation_travel);
        return map;
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

    public int getTotal(){
        return cars_transportation+children+education+fines_fees+food
                +health_beauty+home+insurance+investments_savings+leisure
                +shopping_services+uncategorized+vacation_travel;
    }
}
