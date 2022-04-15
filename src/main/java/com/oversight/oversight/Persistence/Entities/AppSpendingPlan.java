package com.oversight.oversight.Persistence.Entities;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.criteria.CriteriaBuilder;
import java.util.HashMap;

public class AppSpendingPlan {

    private long ID;
    private HashMap<Category, Integer> map;


    public AppSpendingPlan() {
    }

    public AppSpendingPlan(SpendingPlan sp) {
        this.map = sp.getMap();
        this.ID = sp.getID();
    }

    public HashMap<Category, Integer> getMap(){
        return this.map;
    }

    public void setMap(HashMap<Category,Integer> map){
        this.map = map;
    }


    public long getID() {
        return ID;
    }

    public void setID(long ID) {
        this.ID = ID;
    }

}
