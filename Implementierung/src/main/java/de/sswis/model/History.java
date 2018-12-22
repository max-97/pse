package de.sswis.model;


import java.util.HashMap;

/**
 * Ein Objekt zur Zustandsspeicherung von Agenten im Laufe einer Simulation.
 * Jede {@code History} gehört zu einem Agenten.
 * @author Michel Bodé
 */
public class History {
    private int[] score;
    private boolean[] cooperated;
    private Agent[] opponent;
    private String[] strategies;
    private HashMap<Agent, Boolean> cooperatedLastTime;
    private HashMap<Agent, Boolean> cooperatedEveryTime;
    private HashMap<Group, Boolean> groupCooperatedLastTime;
    private HashMap<Group, Boolean> groupCooperatedEveryTime;

    /**
     * Erstellt eine {@code History}.
     * @param rounds Anzahl der Runden der Konfiguration
     */
    public History(int rounds) {

    }

    /**
     * Gibt die Punktzahl des Agenten in einer bestimmten Runde zurück.
     * @param round Runde der gesuchten Punktzahl
     * @return Punktzahl
     */
    public int getScore(int round) {
        return score[round];
    }

    /**
     * Gibt an ob der Gegenspieler des Agenten in einer bestimmten Runde kooperiert hat.
     * @param round Runde der gesuchten Aktion
     * @return wahr wenn er kooperiert hat, falsch sonst
     */
    public boolean getCooperated(int round) {
        return cooperated[round];
    }

    /**
     * Gibt den Gegenspieler des Agenten in einer bestimmten Runde zurück.
     * @param round Runde des gesuchten Gegenspielers
     * @return Gegenspieler
     */
    public Agent getOpponent(int round) {
        return opponent[round];
    }

    /**
     * Gibt den Namen der Strategie des Agenten in einer bestimmten Runde zurück.
     * @param round Runde der gesuchten Strategie
     * @return Name der Strategie
     */
    public String getStrategy(int round) {
        return strategies[round];
    }

    /**
     * Speichert die Punktzahl für die aktuelle Runde.
     * @param score aktuelle Punktzahl
     */
    public void setScore(int score){

    }

    /**
     * Speichert ob der Gegenspieler in der aktuellen Runde kooperiert hat.
     * @param cooperated ob der Gegenspieler kooperiert hat
     */
    public void setCooperated(boolean cooperated) {

    }

    /**
     * Speichert den Gegenspieler für die aktuelle Runde.
     * @param opponent Gegenspieler
     */
    public void setOpponent(Agent opponent) {

    }

    /**
     * Speichert den Namen der Strategie für die aktuelle Runde.
     * @param strategy Name der Strategie
     */
    public void setStrategy(Strategy strategy) {

    }

    /**
     * Gibt zurück ob der Gegenspieler letztes Mal mit diesem Agenten kooperiert hat.
     * @param agent Gegenspieler
     * @return wahr, wenn er kooperiert hat, falsch sonst
     */
    public boolean cooperatedLastTime(Agent agent) {
        return false;
    }

    /**
     * Gibt zurück ob der Gegenspieler jedes Mal mit diesem Agenten kooperiert hat.
     * @param agent Gegenspieler
     * @return wahr, wenn er kooperiert hat, falsch sonst
     */
    public boolean cooperatedEveryTime(Agent agent) {
        return false;
    }

    /**
     * Gibt zurück ob der Gegenspieler beim letzten Spiel gegen einen Agenten aus der Gruppe des Agent
     * um dessen {@code History} es sich handelt, kooperiert hat.
     * @param agent Gegenspieler
     * @return wahr, wenn er kooperiert hat, falsch sonst
     */
    public boolean groupCooperatedLastTime(Agent agent) {
        return false;
    }

    /**
     * Gibt zurück ob der Gegenspieler beim jedem Spiel gegen einen Agenten aus der Gruppe des Agent
     * um dessen {@code History} es sich handelt, kooperiert hat.
     * @param agent Gegenspieler
     * @return wahr, wenn er kooperiert hat, falsch sonst
     */
    public boolean groupCooperatedEveryTime(Agent agent) {
        return false;
    }

}
