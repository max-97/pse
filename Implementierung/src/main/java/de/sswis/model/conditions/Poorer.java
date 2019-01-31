package de.sswis.model.conditions;

import de.sswis.model.Agent;

import java.util.HashMap;

/**
 * Eine Bedingung die erfuellt ist wenn der Gegenspieler aermer ist.
 * Ein Agent ist aermer als ein anderer, wenn er weniger Punkte hat.
 * @author Michel Bodé
 */
public class Poorer implements Condition {

    public static final String NAME = "Ärmer als ich";
    public static final String DESCRIPTION = "";
    private static final String[] PARAMETER_NAMES = {};

    @Override
    public boolean fulfillsCondition(Agent agent1, Agent agent2) {
        return agent1.getScore() > agent2.getScore();
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
