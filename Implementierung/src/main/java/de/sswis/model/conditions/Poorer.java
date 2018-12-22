package de.sswis.model.conditions;

import de.sswis.model.Agent;

/**
 * Eine Bedingung die erfüllt ist wenn der Gegenspieler ärmer ist.
 * Ein Agent ist ärmer als ein anderer, wenn er weniger Punkte hat.
 * @author Michel Bodé
 */
public class Poorer implements Condition {

    public static final String NAME = "";
    public static final String DESCRIPTION = "";

    @Override
    public boolean fulfillsCondition(Agent agent1, Agent agent2) {
        return false;
    }
}
