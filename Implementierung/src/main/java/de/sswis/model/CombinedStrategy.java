package de.sswis.model;

import de.sswis.model.conditions.Condition;
import de.sswis.model.strategies.BaseStrategy;

public class CombinedStrategy {

    private String name;
    private String description;
    private BaseStrategy[] strategies;
    private Condition[] conditions;

    public CombinedStrategy(String name, String description, BaseStrategy[] strategies, Condition[] conditions) {

    }

    /*
    public String getName() {
        return name;
    }
    */

    /*
    public String getDescription() {
        return description;
    }
    */

    public Action calculateAction(Agent agent1, Agent agent2) {
        return null;
    }
}

