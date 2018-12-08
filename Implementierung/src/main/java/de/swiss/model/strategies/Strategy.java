package de.swiss.model.strategies;

import de.swiss.model.Agent;

public abstract class Strategy {
    public abstract boolean cooperate(Agent agent1, Agent agent2);
}
