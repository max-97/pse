package de.sswis.model.conditions;

import de.sswis.model.Agent;

/**
 * Eine Bedingung die immer erfuellt ist.
 * @author Michel Bodé
 */
public class Always implements Condition {

    public static final String NAME = "";
    public static final String DESCRIPTION = "";
    public static final int PARAMETER_COUNT = 0;
    public static final String[] PARAMETER_NAMES = {};

    @Override
    public boolean fulfillsCondition(Agent agent1, Agent agent2) {
        return true;
    }

    @Override
    public String getName() {
        return null;
    }
}
