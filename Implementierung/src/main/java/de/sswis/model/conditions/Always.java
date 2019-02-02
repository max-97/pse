package de.sswis.model.conditions;

import de.sswis.model.Agent;

import java.util.HashMap;

/**
 * Eine Bedingung die immer erfuellt ist.
 * @author Michel Bodé
 */
public class Always implements Condition {

    public static final String NAME = "Immer";
    public static final String DESCRIPTION = "";
    private static final String[] PARAMETER_NAMES = {};

    @Override
    public boolean fulfillsCondition(Agent agent1, Agent agent2) {
        return true;
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
