package de.sswis.model.conditions;

import de.sswis.model.Agent;

/**
 * Eine Bedinung die erfuellt ist, wenn beide Agenten der gleichen Gruppe zugehoerig sind.
 * @author Michel Bodé
 */
public class OwnGroup implements Condition {

    public static final String NAME = "";
    public static final String DESCRIPTION = "";

    @Override
    public boolean fulfillsCondition(Agent agent1, Agent agent2) {
        return false;
    }
}
