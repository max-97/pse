package de.sswis.model.conditions;

import de.sswis.model.Agent;

/**
 * Eine Bedingung die erfuellt ist, wenn beide Agenten ungefaehr gleich reich sind.
 * Zwei Agenten sind ungefaehr gleich reich, wenn der Betrag der Differenz ihrer Gesamtpunktzahlen kleiner
 * oder gleich {@code delta} ist.
 * @author Michel Bodé
 */
public class Delta implements Condition{

    public static final String NAME = "";
    public static final String DESCRIPTION = "";
    public static final int PARAMETER_COUNT = 1;
    public static final String[] PARAMETER_NAMES = {"DELTA"};
    private final double DELTA;

    /**
     * Konstruktor
     * @param DELTA maximaler Betrag der Differenz der Gesamtpunktzahlen, der die Bedingung erfuellt
     */
    public Delta(double DELTA) {
        this.DELTA = DELTA;
    }

    @Override
    public boolean fulfillsCondition(Agent agent1, Agent agent2) {
        return Math.abs(agent1.getScore() - agent2.getScore()) <= DELTA;
    }
}
