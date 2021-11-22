package com.oversight.oversight.Persistence.Entities;

import javax.lang.model.element.Element;

public enum Category {
    CARS("Cars & transportation"),
    CHILDREN("Children"),
    EDUCATION("Education"),
    FINES("Fines & Fees"),
    FOOD("Food"),
    HEALTH("health & Beauty"),
    HOME("Home"),
    INSUREANCE("Insurance"),
    INVESTMENTS("Investments & Savings"),
    LEISURE("Leisure"),
    SHOPPING("Shopping & Services"),
    MISC("Mmiscellaneous"),
    TRAVEL("Vacation and Travel");


    private final String displayName;

    Category(final String displayName){
        this.displayName = displayName;
    }

    public String getDisplayName(){
        return displayName;
    }
}
