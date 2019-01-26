package de.sswis.model.conditions;

import de.sswis.model.Agent;

/**
 * Eine Bedingung die mit einer gewissen Wahrscheinlichkeit {@code alpha} erfuellt ist.
 * @author Michel Bodé
 */
public class Probability implements Condition {

    public static final String NAME = "";
    public static final String DESCRIPTION = "";
    public static final int PARAMETER_COUNT = 1;
    public static final String[] PARAMETER_NAMES = {"ALPHA"};
    private final double ALPHA;

    /**
     * Konstruktor
     * @param ALPHA Wahrscheinlichkeit mit der die Bedingung erfuellt ist
     */
    public Probability(double ALPHA) {
        this.ALPHA = ALPHA;
    }

    @Override
    public boolean fulfillsCondition(Agent agent1, Agent agent2) {
        double random = Math.random();
        return random < ALPHA;
    }

    @Override
    public String getName() {
        return null;
    }

    @Override
    public void setParameter(double conditionParameter) {

    }
}
