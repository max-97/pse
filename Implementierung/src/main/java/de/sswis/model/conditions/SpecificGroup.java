package de.sswis.model.conditions;

import de.sswis.model.Agent;

/**
 * Eine Bedingung die erfüllt ist, wenn der Gegenspieler Mitglied einer spezifischen Gruppe ist.
 * @author Michel Bodé
 */
public class SpecificGroup extends Condition {

    public static final String NAME = "";
    public static final String DESCRIPTION = "";
    private int groupID;

    /**
     * Konstruktor
     * @param groupID Gruppen-ID der Gruppe deren Mitglieder die Bedingung erfüllen
     */
    public SpecificGroup(int groupID) {

    }

    @Override
    public boolean fulfillsCondition(Agent agent1, Agent agent2) {
        return false;
    }
}
