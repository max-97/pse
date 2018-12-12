package de.sswis.model.conditions;

import de.sswis.model.Agent;

public abstract class Condition {
    public abstract boolean fulfillsCondition(Agent agent1, Agent agent2);
}
