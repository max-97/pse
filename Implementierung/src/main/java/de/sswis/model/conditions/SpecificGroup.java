package de.sswis.model.conditions;

import de.sswis.model.Agent;

import java.util.HashMap;

/**
 * Eine Bedingung die erfuellt ist, wenn der Gegenspieler Mitglied einer spezifischen Gruppe ist.
 * @author Michel Bodé
 */
public class SpecificGroup implements Condition {

    public static final String NAME = "Spezifische Gruppe";
    public static final String DESCRIPTION = "";
    public static final int PARAMETER_COUNT = 1;
    public static final String[] PARAMETER_NAMES = {"GROUP_ID"};
    private int groupId;

    public SpecificGroup() { groupId = 1; }

    @Override
    public boolean fulfillsCondition(Agent agent1, Agent agent2) {
        return agent2.getGroup().getId() == groupId;
    }

    @Override
    public String getName() {
        return NAME;
    }

    @Override
    public void setParameter(HashMap<String, Object> parameters) {
        groupId = (int)parameters.get("GROUP_ID");
    }
}
