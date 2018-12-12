package de.sswis.model.strategies;

import de.sswis.model.Action;
import de.sswis.model.Agent;

public abstract class BaseStrategy {
    public abstract Action calculateAction(Agent agent1, Agent agent2);
}
