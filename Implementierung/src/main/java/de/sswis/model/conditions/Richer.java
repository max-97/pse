package de.sswis.model.conditions;

import de.sswis.model.Agent;

import java.util.HashMap;

/**
 * Eine Bedingung die erfuellt ist wenn der Gegenspieler reicher ist.
 * Ein Agent ist aermer als ein anderer, wenn er mehr Punkte hat.
 * @author Michel Bodé
 */
public class Richer implements Condition {

    public static final String NAME = "Reicher als ich";
    public static final String DESCRIPTION = "";
    private static final String[] PARAMETER_NAMES = {};

    @Override
    public boolean fulfillsCondition(Agent agent1, Agent agent2) {
        return agent1.getScore() < agent2.getScore();
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
