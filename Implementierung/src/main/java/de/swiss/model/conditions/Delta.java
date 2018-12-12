package de.swiss.model.conditions;

import de.swiss.model.Agent;

public class Delta extends Condition{

    public static final String NAME = "";
    public static final String DESCRIPTION = "";
    private double delta;

    public Delta(double delta) {

    }

    @Override
    public boolean fulfillsCondition(Agent agent1, Agent agent2) {
        return false;
    }
}
