package de.sswis.model.conditions;

import de.sswis.model.Agent;

/**
 * Eine Bedingung die erfuellt ist wenn der Gegenspieler reicher ist.
 * Ein Agent ist aermer als ein anderer, wenn er mehr Punkte hat.
 * @author Michel Bodé
 */
public class Richer implements Condition {

    public static final String NAME = "";
    public static final String DESCRIPTION = "";

    @Override
    public boolean fulfillsCondition(Agent agent1, Agent agent2) {
        return false;
    }
}
