package de.sswis.model;

/**
 * Ein Paar von Agenten welche ein vorgegebenes Stufenspiel spielen können.
 * @author Michel Bodé
 */
public class Pair {
    private Agent agent1;
    private Agent agent2;
    private Game game;

    /**
     * Erstellt ein Paar von Agenten.
     * @param agent1 erster Agent
     * @param agent2 zweiter Agent
     * @param game zu spielendes Stufenspiel
     */
    public Pair(Agent agent1, Agent agent2, Game game) {

    }

    /**
     * Das vorgegebene Stufenspiel wird von den zwei Agenten gespielt, entsprechend ihrer Aktionen werden
     * die Payoffs verteilt.
     */
    public void playGame() {

    }
}
