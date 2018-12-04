package model.strategies;

import model.Agent;

public abstract class Strategy {
    public abstract boolean cooperate(Agent agent1, Agent agent2);
}
