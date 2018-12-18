package de.sswis.model.conditions;

import de.sswis.model.Agent;

/**
 * Eine Bedingung die erfüllt ist, wenn beide Agenten ungefähr gleich reich sind.
 * Zwei Agenten sind ungefähr gleich reich, wenn der Betrag der Differenz ihrer Gesamtpunktzahlen kleiner
 * oder gleich {@code delta} ist.
 * @author Michel Bodé
 */
public class Delta extends Condition{

    public static final String NAME = "";
    public static final String DESCRIPTION = "";
    private double delta;

    /**
     * Konstruktor
     * @param delta maximaler Betrag der Differenz der Gesamtpunktzahlen, der die Bedingung erfüllt
     */
    public Delta(double delta) {

    }

    @Override
    public boolean fulfillsCondition(Agent agent1, Agent agent2) {
        return false;
    }
}
