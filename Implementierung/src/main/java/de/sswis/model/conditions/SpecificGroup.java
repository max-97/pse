package de.sswis.model.conditions;

import de.sswis.model.Agent;

public class SpecificGroup extends Condition {

    public static final String NAME = "";
    public static final String DESCRIPTION = "";
    private int groupID;

    public SpecificGroup(int groupID) {

    }

    @Override
    public boolean fulfillsCondition(Agent agent1, Agent agent2) {
        return false;
    }
}
