package de.swiss.model.strategies;

import de.swiss.model.Action;
import de.swiss.model.Agent;

public abstract class BaseStrategy {
    public abstract Action calculateAction(Agent agent1, Agent agent2);
}
