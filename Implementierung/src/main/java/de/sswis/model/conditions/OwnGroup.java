package de.sswis.model.conditions;

import de.sswis.model.Agent;

import java.util.HashMap;

/**
 * Eine Bedinung die erfuellt ist, wenn beide Agenten der gleichen Gruppe zugehoerig sind.
 * @author Michel Bodé
 */
public class OwnGroup implements Condition {

    public static final String NAME = "Eigene Gruppe";
    public static final String DESCRIPTION = "";
    private static final String[] PARAMETER_NAMES = {};

    @Override
    public boolean fulfillsCondition(Agent agent1, Agent agent2) {
        return agent1.getGroup().getId() == agent2.getGroup().getId() ;
    }

    @Override
    public String getName() {
        return NAME;
    }

    @Override
    public void setParameters(HashMap<String, Object> parameters) {

    }

    @Override
    public String[] getParameters() {
        return PARAMETER_NAMES;
    }
}
