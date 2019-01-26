package de.sswis.model.conditions;

import de.sswis.model.Agent;

/**
 * Eine Bedinung die erfuellt ist, wenn beide Agenten der gleichen Gruppe zugehoerig sind.
 * @author Michel Bodé
 */
public class OwnGroup implements Condition {

    public static final String NAME = "";
    public static final String DESCRIPTION = "";
    public static final int PARAMETER_COUNT = 0;
    public static final String[] PARAMETER_NAMES = {};

    @Override
    public boolean fulfillsCondition(Agent agent1, Agent agent2) {
        return agent1.getGroup().getId() == agent2.getGroup().getId() ;
    }

    @Override
    public String getName() {
        return null;
    }

    @Override
    public void setParameter(double conditionParameter) {

    }
}
