package de.sswis.model.conditions;

import de.sswis.model.Agent;

/**
 * Eine Bedingung die mit einer gewissen Wahrscheinlichkeit {@code alpha} erfuellt ist.
 * @author Michel Bodé
 */
public class Probability implements Condition {

    public static final String NAME = "";
    public static final String DESCRIPTION = "";
    private double alpha;

    /**
     * Konstruktor
     * @param alpha Wahrscheinlichkeit mit der die Bedingung erfuellt ist
     */
    public Probability(double alpha) {

    }

    @Override
    public boolean fulfillsCondition(Agent agent1, Agent agent2) {
        return false;
    }
}
