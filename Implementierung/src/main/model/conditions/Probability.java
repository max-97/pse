package model.conditions;

import model.Agent;

public class Probability extends Condition {

    public static final String NAME = "";
    public static final String DESCRIPTION = "";
    private double alpha;

    public Probability(double alpha) {

    }

    @Override
    public boolean fulfillsCondition(Agent agent1, Agent agent2) {
        return false;
    }
}
