package com.oversight.oversight.Services.Implementations;

import com.oversight.oversight.Persistence.Entities.SpendingPlan;
import com.oversight.oversight.Persistence.Entities.User;
import com.oversight.oversight.Persistence.Repositories.SpendingPlanRepository;
import com.oversight.oversight.Services.SpendingPlanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class SpendingPlanServiceImplementation implements SpendingPlanService {
    private SpendingPlanRepository spendingPlanRepository;

    @Autowired
    public SpendingPlanServiceImplementation(SpendingPlanRepository spendingPlanRepository) {
        this.spendingPlanRepository = spendingPlanRepository;
    }

    @Override
    public SpendingPlan save(SpendingPlan spendingPlan) {
        return spendingPlanRepository.save(spendingPlan);
    }

    @Override
    public void delete(SpendingPlan spendingPlan) {
        spendingPlanRepository.delete(spendingPlan);
    }

    @Override
    public SpendingPlan findByUser(User user) {
        return spendingPlanRepository.findByUser(user);
    }

    @Override
    public SpendingPlan createSpendingPlan(User user, String generate) {
        if (generate.equals("no")) return null;
        SpendingPlan sp = SpendingPlan.createRandom(user);
        return spendingPlanRepository.save(sp);
    }

    @Override
    public ArrayList<ArrayList<Object>> getPieChartData(User user) {

        //Create dictionary
        HashMap<String, Integer> map = new HashMap<String, Integer>();

        //Create empty list
        ArrayList<ArrayList<Object>> chartData = new ArrayList<ArrayList<Object>>();

        //get all transactions
        SpendingPlan spendingPlan = spendingPlanRepository.findByUser(user);

        //Add each transaction to the empty list as a pair of category and amount
        map.put("Cars & Transportation", spendingPlan.getCars_transportation());
        map.put("Children", spendingPlan.getChildren());
        map.put("Education", spendingPlan.getEducation());
        map.put("Fines & Fees", spendingPlan.getFines_fees());
        map.put("Food", spendingPlan.getFood());
        map.put("Health & Beauty", spendingPlan.getHealth_beauty());
        map.put("Home", spendingPlan.getHome());
        map.put("Insurance", spendingPlan.getInsurance());
        map.put("Investments & Savings", spendingPlan.getInvestments_savings());
        map.put("Leisure", spendingPlan.getLeisure());
        map.put("Shopping & Services", spendingPlan.getShopping_services());
        map.put("Uncategorized", spendingPlan.getUncategorized());
        map.put("Vacation & Travel", spendingPlan.getVacation_travel());

        Iterator<Map.Entry<String, Integer>> entrySet = map.entrySet().iterator();

        while(entrySet.hasNext()){
            Map.Entry<String, Integer> entry = entrySet.next();
            ArrayList<Object> temp = new ArrayList<Object>();
            temp.add(entry.getKey());
            temp.add(entry.getValue());
            chartData.add(temp);
        }

        return chartData;
    }

    @Override
    public SpendingPlan findByID(long id) {
        return spendingPlanRepository.findByID(id);
    }
}
