package de.sswis.model.conditions;

import de.sswis.model.Agent;

/**
 * Eine Bedingung die mit einer gewissen Wahrscheinlichkeit {@code alpha} erfuellt ist.
 * @author Michel Bodé
 */
public class Probability implements Condition {

    public static final String NAME = "Wahrscheinlichkeit";
    public static final String DESCRIPTION = "";
    public static final int PARAMETER_COUNT = 1;
    public static final String[] PARAMETER_NAMES = {"ALPHA"};
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
    public void setParameter(double conditionParameter) {
        alpha = conditionParameter;
    }
}
