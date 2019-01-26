package de.sswis.model;

/**
 * Ein Paar von Agenten.
 * @author Michel Bodé
 */
public class Pair {
    private Agent agent1;
    private Agent agent2;

    /**
     * Erstellt ein Paar von Agenten.
     * @param agent1 erster Agent
     * @param agent2 zweiter Agent
     */
    public Pair(Agent agent1, Agent agent2) {
         this.agent1 = agent1;
         this.agent2 = agent2;
    }
    /**
     * Gibt einen bestimmten Agenten des Paars zurück.
     * @param agentNumber Nummer des Agenten, eins für den Ersten, zwei für den Zweiten
     * @return spezifizierter Agent
     */
    public Agent getAgent(int agentNumber) {
        if(agentNumber == 1) {
            return agent1;
        } else if(agentNumber == 2) {
            return agent2;
        } else {
            return null;
        }
    }

}
