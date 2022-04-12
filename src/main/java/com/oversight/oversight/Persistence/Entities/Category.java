package com.oversight.oversight.Persistence.Entities;

import java.util.Random;

public enum Category {
    CARS("Cars & transportation"),
    CHILDREN("Children"),
    EDUCATION("Education"),
    FINES("Fines & Fees"),
    FOOD("Food"),
    HEALTH("health & Beauty"),
    HOME("Home"),
    INSURANCE("Insurance"),
    INVESTMENTS("Investments & Savings"),
    LEISURE("Leisure"),
    MISC("Miscellaneous"),
    SHOPPING("Shopping & Services"),
    TRAVEL("Vacation and Travel");


    /**
     * gefur random category
     * @return random category
     */
    public static Category getRandomCategory(){
        Category[] values = Category.values();
        int length = values.length;
        int randIndex = new Random().nextInt(length);
        return values[randIndex];
    }

    private final String displayName;

    Category(final String displayName){
        this.displayName = displayName;
    }

    public String getDisplayName(){
        return displayName;
    }
}
