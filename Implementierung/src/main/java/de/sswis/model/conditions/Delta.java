package de.sswis.model.conditions;

import de.sswis.model.Agent;

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
