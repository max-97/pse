package de.swiss.model.conditions;

import de.swiss.model.Agent;

public class OwnGroup extends Condition {

    public static final String NAME = "";
    public static final String DESCRIPTION = "";

    @Override
    public boolean fulfillsCondition(Agent agent1, Agent agent2) {
        return false;
    }
}
