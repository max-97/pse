package de.swiss.model.conditions;

import de.swiss.model.Agent;

public abstract class Condition {
    public abstract boolean fulfillsCondition(Agent agent1, Agent agent2);
}
