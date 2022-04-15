package com.oversight.oversight.Persistence.Entities;

import javax.persistence.*;
import javax.persistence.criteria.CriteriaBuilder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

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

    public SpendingPlan(int[] cats, User user) {
        this.cars_transportation = cats[0];
        this.children = cats[1];
        this.education = cats[2];
        this.fines_fees = cats[3];
        this.food = cats[4];
        this.health_beauty = cats[5];
        this.home = cats[6];
        this.insurance = cats[7];
        this.investments_savings = cats[8];
        this.leisure = cats[9];
        this.shopping_services = cats[10];
        this.uncategorized = cats[11];
        this.vacation_travel = cats[12];

        this.user = user;

    }

    public static SpendingPlan createFromMap(HashMap<Category, Integer> map, User user){
        SpendingPlan sp = new SpendingPlan();
        ArrayList<Category> cats = Category.getCategories();

        int[] categories = new int[13];
        int index = 0;
        for (Category c : cats){
            int val = 0;
            if (map.containsKey(c)){
                val = map.get(c);
            }
            categories[index++] = val;
        }
        return new SpendingPlan(categories, user);
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

    public HashMap<String, Integer> getMapForApp(){
        HashMap<Category,  Integer> map = this.getMap();
        HashMap<String, Integer> newMap = new HashMap<>();

        Iterator m = map.entrySet().iterator();

        while (m.hasNext()){
            Map.Entry entry = (Map.Entry) m.next();
            Category c = (Category) entry.getKey();
            int v = (int)entry.getValue();
            String cat = c.name();
            newMap.put(cat, v);
        }
        newMap.put("id", (int)this.getID());
        return newMap;
    }

    public static SpendingPlan createRandom(User user){
        int maxAmount = 100000;
        int minAmount = 4000;

        SpendingPlan sp = new SpendingPlan();

        sp.setCars_transportation((int)(Math.random()*(maxAmount-minAmount)+minAmount));
        sp.setChildren((int)(Math.random()*(maxAmount-minAmount)+minAmount));
        sp.setEducation((int)(Math.random()*(maxAmount-minAmount)+minAmount));
        sp.setFood((int)(Math.random()*(maxAmount-minAmount)+minAmount));
        sp.setFines_fees((int)(Math.random()*(maxAmount-minAmount)+minAmount));
        sp.setHealth_beauty((int)(Math.random()*(maxAmount-minAmount)+minAmount));
        sp.setHome((int)(Math.random()*(maxAmount-minAmount)+minAmount));
        sp.setInsurance((int)(Math.random()*(maxAmount-minAmount)+minAmount));
        sp.setInvestments_savings((int)(Math.random()*(maxAmount-minAmount)+minAmount));
        sp.setLeisure((int)(Math.random()*(maxAmount-minAmount)+minAmount));
        sp.setShopping_services((int)(Math.random()*(maxAmount-minAmount)+minAmount));
        sp.setUncategorized((int)(Math.random()*(maxAmount-minAmount)+minAmount));
        sp.setVacation_travel((int)(Math.random()*(maxAmount-minAmount)+minAmount));

        sp.user = user;
        return sp;
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

    public int getTotal(){
        return cars_transportation+children+education+fines_fees+food
                +health_beauty+home+insurance+investments_savings+leisure
                +shopping_services+uncategorized+vacation_travel;
    }

}
