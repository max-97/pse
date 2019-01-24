package de.sswis.model;


import java.util.ArrayList;
import java.util.HashMap;

/**
 * Ein Objekt zur Zustandsspeicherung von Agenten im Laufe einer Simulation.
 * Jede {@code History} gehoert zu einem Agenten.
 * @author Michel Bodé
 */
public class History {
    private int currentRound;
    private ArrayList<Integer> score;
    private ArrayList<Boolean> cooperated;
    private ArrayList<Agent> opponent;
    private ArrayList<String> strategies;
    private boolean alwaysCooperated;
    private HashMap<Agent, Boolean> cooperatedLastTime;
    private HashMap<Agent, Boolean> cooperatedEveryTime;
    private HashMap<Group, Boolean> groupCooperatedLastTime;
    private HashMap<Group, Boolean> groupCooperatedEveryTime;
    private final int INITIAL_CAPACITY = 1000;

    /**
     * Erstellt eine {@code History}.
     */
    public History() {
        this.currentRound = 0;
        this.score = new ArrayList<>(INITIAL_CAPACITY);
        this.cooperated = new ArrayList<>(INITIAL_CAPACITY);
        this.opponent = new ArrayList<>(INITIAL_CAPACITY);
        this.strategies = new ArrayList<>(INITIAL_CAPACITY);
        this.alwaysCooperated = true;
        this.cooperatedLastTime = new HashMap<Agent, Boolean>();
        this.cooperatedEveryTime = new HashMap<Agent, Boolean>();
        this.groupCooperatedLastTime = new HashMap<Group, Boolean>();
        this.groupCooperatedEveryTime = new HashMap<Group, Boolean>();
    }

    /**
     * Gibt zurück ob der Agent bis zur aktuellen Runde immer kooperiert hat.
     * @return wahr falls er immer kooperiert hat, falsch sonst
     */
    public boolean getAlwaysCooperated() {
        return alwaysCooperated;
    }

    /**
     * Gibt die Punktzahl des Agenten aus der letzten Runde zurück zurück.
     * @return aktuelle Punktzahl
     */
    public int getScore() { return score.get(currentRound); }

    /**
     * Gibt die Punktzahl des Agenten in einer bestimmten Runde zurueck.
     * @param round Runde der gesuchten Punktzahl
     * @return Punktzahl
     */
    public int getScore(int round) {
        return score.get(round);
    }

    /**
     * Gibt an ob der Gegenspieler des Agenten in der letzten Runde kooperiert hat.
     * @return wahr wenn er kooperiert hat, falsch sonst
     */
    public boolean getCooperated() {
        return cooperated.get(currentRound);
    }

    /**
     * Gibt an ob der Gegenspieler des Agenten in einer bestimmten Runde kooperiert hat.
     * @param round Runde der gesuchten Aktion
     * @return wahr wenn er kooperiert hat, falsch sonst
     */
    public boolean getCooperated(int round) {
        return cooperated.get(round);
    }

    /**
     * Gibt den Gegenspieler des Agenten in einer bestimmten Runde zurueck.
     * @param round Runde des gesuchten Gegenspielers
     * @return Gegenspieler
     */
    public Agent getOpponent(int round) {
        return opponent.get(round);
    }

    /**
     * Gibt den Namen der Strategie des Agenten in einer bestimmten Runde zurueck.
     * @param round Runde der gesuchten Strategie
     * @return Name der Strategie
     */
    public String getStrategy(int round) {
        return strategies.get(round);
    }

    /**
     * Speichert die Punktzahl fuer die aktuelle Runde.
     * @param score aktuelle Punktzahl
     */
    public void setScore(int score){
        this.score.add(currentRound, score);
    }

    /**
     * Speichert ob der Gegenspieler in der aktuellen Runde kooperiert hat.
     * @param cooperated ob der Gegenspieler kooperiert hat
     */
    public void setCooperated(boolean cooperated) {
        this.cooperated.add(currentRound, cooperated);
    }

    /**
     * Speichert den Gegenspieler fuer die aktuelle Runde.
     * @param opponent Gegenspieler
     */
    public void setOpponent(Agent opponent) {
        this.opponent.add(currentRound, opponent);
    }

    /**
     * Speichert den Namen der Strategie fuer die aktuelle Runde.
     * @param strategy Name der Strategie
     */
    public void setStrategy(Strategy strategy) {
        this.strategies.add(currentRound, strategy.getName());
    }

    /**
     * Speichert ob der Agent in den bisherigen Runden immer kooperiert hat.
     * @param alwaysCooperated Wert der angibt ob er immer kooperiert hat
     */
    public void setAlwaysCooperated(boolean alwaysCooperated) {
        this.alwaysCooperated = alwaysCooperated;
    }

    /**
     * Gibt zurueck ob der Gegenspieler letztes Mal mit diesem Agenten kooperiert hat.
     * @param agent Gegenspieler
     * @return wahr, wenn er kooperiert hat, falsch sonst
     */
    public boolean cooperatedLastTime(Agent agent) {
        return false;
    }

    /**
     * Gibt zurueck ob der Gegenspieler jedes Mal mit diesem Agenten kooperiert hat.
     * @param agent Gegenspieler
     * @return wahr, wenn er kooperiert hat, falsch sonst
     */
    public boolean cooperatedEveryTime(Agent agent) {
        return false;
    }

    /**
     * Gibt zurueck ob dieser Agent beim letzten Spiel gegen einen Agenten aus einer bestimmten Gruppe kooperiert hat.
     * @param group spezifische Gruppe
     * @return wahr, wenn er kooperiert hat, falsch sonst
     */
    public boolean groupCooperatedLastTime(Group group) {
        return false;
    }

    /**
     * Gibt zurueck ob dieser Agent bei jedem vorherigen Spiel gegen einen Agenten aus einer bestimmten Gruppe
     * kooperiert hat.
     * @param group spezifische Gruppe
     * @return wahr, wenn er kooperiert hat, falsch sonst
     */
    public boolean groupCooperatedEveryTime(Group group) {
        return false;
    }

    /**
     * Speichert ob ein Gegenspieler im letzten Spiel gegen diesen Agenten kooperiert hat.
     * @param agent Gegenspieler
     * @param cooperated wahr wenn er kooperiert hat, falsch sonst
     */
    public void setCooperatedLastTime(Agent agent, boolean cooperated){

    }

    /**
     * Speichert ob ein Gegenspieler in jedem vorherigen Spiel gegen diesen Agenten kooperiert hat.
     * @param agent Gegenspieler
     * @param cooperated wahr wenn er kooperiert hat, falsch sonst
     */
    public void setCooperatedEveryTime(Agent agent, boolean cooperated) {

    }

    /**
     * Speichert ob dieser Agent beim letzten Spiel gegen einen Gegenspieler einer spezifischen Gruppe
     * kooperiert hat.
     * @param group Gruppe des Gegenspielers
     * @param cooperated wahr wenn er kooperiert hat, falsch sonst
     */
    public void setGroupCooperatedLastTime(Group group, boolean cooperated) {

    }

    /**
     * Speichert ob dieser Agent bei jedem vorherigen letzten Spiel gegen einen Gegenspieler einer
     * spezifischen Gruppe kooperiert hat.
     * @param group Gruppe des Gegenspielers
     * @param cooperated wahr wenn er kooperiert hat, falsch sonst
     */
    public void setGroupCooperatedEveryTime(Group group, boolean cooperated) {

    }

    /**
     * Gibt die aktuelle Rundenzahl zurück.
     * @return Rundenzahl
     */
    public int getCurrentRound() {
        return currentRound;
    }

    public int getCurrentCycle() { return 0; }

    /**
     * Inkrementiert den Rundenzähler um eins.
     */
    public void increaseRoundCount() {
        currentRound++;
    }
}
