package model;

import model.conditions.Condition;
import model.strategies.Strategy;

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

    public boolean cooperate(Agent agent1, Agent agent2) {
        return false;
    }
}

