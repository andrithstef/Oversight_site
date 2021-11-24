package com.oversight.oversight.Persistence.Entities;

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
    SHOPPING("Shopping & Services"),
    MISC("Miscellaneous"),
    TRAVEL("Vacation and Travel");


    private final String displayName;

    Category(final String displayName){
        this.displayName = displayName;
    }

    public String getDisplayName(){
        return displayName;
    }
}
