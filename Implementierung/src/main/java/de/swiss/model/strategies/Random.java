package de.swiss.model.strategies;

import de.swiss.model.Agent;

public class Random extends Strategy{
    public final static String NAME = "";
    public final static String DESCRIPTION = "";

    @Override
    public boolean cooperate(Agent agent1, Agent agent2) {
        return false;
    }
}
