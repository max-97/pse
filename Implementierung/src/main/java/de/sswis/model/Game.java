package de.sswis.model;


/**
 * Ein Stufenspiel, welches durch seine Payoffs definiert ist.
 * Ein Spiel wird von zwei Agenten gespielt und, abhaengig von deren Aktionen, erhalten diese Payoffs.
 * @author Michel Bodé
 */
public class Game {

    private String name;
    private String description;
    private Tuple[][] payoffs;


    /**
     * Erstellt ein Stufenspiel.
     * @param name Name des Spiels
     * @param description Beschreibung des Spiels
     * @param payoffs Payoffs des Spiels
     */
    public Game(String name, String description, Tuple[][] payoffs) {

    }

    /**
     * Gibt die Payoffs zu bestimmten Aktionen zurueck.
     * @param a1 Aktion des ersten Agenten
     * @param a2 Aktion des zweiten Agenten
     * @return ein Paar von Payoffs
     */
    public Tuple getPayoffs(Action a1, Action a2) {
        return null;
    }

    /**
     * Das Stufenspiel wird von den zwei Agenten gespielt, entsprechend ihrer Aktionen werden
     * die Payoffs verteilt.
     * @param pair Agentenpaar
     */
    public void playGame(Pair pair) {

    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public class Tuple {
        public int x;
        public int y;

        public Tuple(int x, int y) {

        }

    }

}
