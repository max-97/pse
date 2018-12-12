package de.sswis.model.conditions;

import de.sswis.model.Agent;

public class Always extends Condition {

    public static final String NAME = "";
    public static final String DESCRIPTION = "";

    @Override
    public boolean fulfillsCondition(Agent agent1, Agent agent2) {
        return true;
    }
}
