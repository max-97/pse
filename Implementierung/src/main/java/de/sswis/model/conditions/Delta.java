package de.sswis.model.conditions;

import de.sswis.model.Agent;

import java.util.HashMap;

/**
 * Eine Bedingung die erfuellt ist, wenn beide Agenten ungefaehr gleich reich sind.
 * Zwei Agenten sind ungefaehr gleich reich, wenn der Betrag der Differenz ihrer Gesamtpunktzahlen kleiner
 * oder gleich {@code delta} ist.
 * @author Michel Bodé
 */
public class Delta implements Condition{

    public static final String NAME = "Delta";
    public static final String DESCRIPTION = "";
    public static final int PARAMETER_COUNT = 1;
    public static final String[] PARAMETER_NAMES = {"DELTA"};
    private double delta;

    public Delta() {
        delta = 0.5;
    }

    @Override
    public boolean fulfillsCondition(Agent agent1, Agent agent2) {
        return Math.abs(agent1.getScore() - agent2.getScore()) <= delta;
    }

    @Override
    public String getName() {
        return NAME;
    }

    @Override
    public void setParameter(HashMap<String, Object> parameters) {
        delta = (double)parameters.get("DELTA");
    }
}
