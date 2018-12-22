package de.sswis.model;

/**
 * Eine Initialisierung welche die in ihr enthaltenen Agenten und Gruppen festlegt.
 * @author Michel Bodé
 */
public class Initialization {
    private Group[] groups;
    private int agentCount;
    private Agent[] agents;


    /**
     * Erstellt eine Initialisierung.
     * @param groups enthaltene Gruppen
     * @param agentCount Anzahl der Agenten
     * @param agents Menge von enthaltenen Agenten
     */
    public Initialization(Group[] groups, int agentCount, Agent[] agents) {

    }

    public Group[] getGroups () {
        return groups;
    }

    public int getAgentCount() {
        return agentCount;
    }

    public Agent[] getAgents() {
        return agents;
    }

}
