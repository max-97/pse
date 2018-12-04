package model.conditions;

import model.Agent;

/**
 *
 */
public abstract class Condition {


    /**
     *
     * @param agent1
     * @param agent2
     * @return
     */
    public abstract boolean fulfillsCondition(Agent agent1, Agent agent2);
}
