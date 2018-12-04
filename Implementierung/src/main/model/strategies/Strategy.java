package model.strategies;

import model.Agent;

/**
 *
 */
public abstract class Strategy {

    /**
     *
     * @param agent1
     * @param agent2
     * @return
     */
    public abstract boolean cooperate(Agent agent1, Agent agent2);
}
