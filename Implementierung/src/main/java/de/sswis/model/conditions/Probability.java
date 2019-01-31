package de.sswis.model.conditions;

import de.sswis.model.Agent;

import java.util.HashMap;

/**
 * Eine Bedingung die mit einer gewissen Wahrscheinlichkeit {@code alpha} erfuellt ist.
 * @author Michel Bodé
 */
public class Probability implements Condition {

    public static final String NAME = "Wahrscheinlichkeit";
    public static final String DESCRIPTION = "";
    private static final String[] PARAMETER_NAMES = {"ALPHA"};
    private double alpha;

    public Probability() {
        alpha = 0.5;
    }


    @Override
    public boolean fulfillsCondition(Agent agent1, Agent agent2) {
        double random = Math.random();
        return random < alpha;
    }

    @Override
    public String getName() {
        return NAME;
    }

    @Override
    public void setParameters(HashMap<String, Object> parameters) {
        alpha = (double)parameters.get("APLHA");
    }

    @Override
    public String[] getParameters() {
        return PARAMETER_NAMES;
    }
}
