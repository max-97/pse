package model.strategies;

import model.Agent;

public class NeverCooperate extends Strategy{
    public final static String NAME = "";
    public final static String DESCRIPTION = "";

    @Override
    public boolean cooperate(Agent agent1, Agent agent2) {
        return false;
    }
}
