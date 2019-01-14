package de.sswis.model.conditions;

import de.sswis.model.Agent;

/**
 * Eine Bedingung die erfuellt ist, wenn der Gegenspieler Mitglied einer spezifischen Gruppe ist.
 * @author Michel Bodé
 */
public class SpecificGroup implements Condition {

    public static final String NAME = "";
    public static final String DESCRIPTION = "";
    public static final int PARAMETER_COUNT = 0;
    public static final String[] PARAMETER_NAMES = {};
    private final int GROUP_ID;

    /**
     * Konstruktor
     * @param GROUP_ID Gruppen-ID der Gruppe deren Mitglieder die Bedingung erfuellen
     */
    public SpecificGroup(int GROUP_ID) {
        this.GROUP_ID = GROUP_ID;
    }

    @Override
    public boolean fulfillsCondition(Agent agent1, Agent agent2) {
        return false;
    }
}
