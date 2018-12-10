package de.swiss.model;

import de.swiss.model.strategies.Strategy;
import de.swiss.model.conditions.Condition;

public class CombinedStrategy {

    private String name;
    private Strategy[] strategies;
    private Condition[] conditions;
    private String description;

    public CombinedStrategy(String name, Strategy[] strategies, Condition[] conditions) {

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

