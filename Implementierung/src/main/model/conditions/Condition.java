package model.conditions;

import model.Agent;

public abstract class Condition {
    public abstract boolean fulfillsCondition(Agent agent1, Agent agent2);
}
