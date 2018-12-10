package de.swiss.model;

import de.swiss.model.strategies.BaseStrategy;
import de.swiss.model.conditions.Condition;

public class CombinedStrategy implements Strategy{

    private String name;
    private BaseStrategy[] strategies;
    private Condition[] conditions;
    private String description;

    public CombinedStrategy(String name, String description, BaseStrategy[] strategies, Condition[] conditions) {

    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public Action calculateAction(Agent agent1, Agent agent2) {
        return null;
    }
}

