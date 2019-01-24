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
    private int currentCycle;
    private ArrayList<Integer> score;
    private ArrayList<Boolean> opponentCooperated;
    private ArrayList<Agent> opponent;
    private ArrayList<String> strategies;
    private boolean alwaysCooperated;
    private HashMap<Agent, Boolean> cooperatedLastTime;
    private HashMap<Agent, Boolean> cooperatedEveryTime;
    private HashMap<Group, Boolean> groupCooperatedLastTime;
    private HashMap<Group, Boolean> groupCooperatedEveryTime;
    private final int INITIAL_CAPACITY_ROUNDS = 1000;
    private final int INITIAL_CAPACITY_CYCLE = 100;

    /**
     * Erstellt eine {@code History}.
     */
    public History() {
        this.currentRound = 0;
        this.currentCycle = 1;
        this.score = new ArrayList<>(INITIAL_CAPACITY_CYCLE);
        this.opponentCooperated = new ArrayList<>(INITIAL_CAPACITY_ROUNDS);
        this.opponent = new ArrayList<>(INITIAL_CAPACITY_ROUNDS);
        this.strategies = new ArrayList<>(INITIAL_CAPACITY_CYCLE);
        this.alwaysCooperated = true;
        this.cooperatedLastTime = new HashMap<>();
        this.cooperatedEveryTime = new HashMap<>();
        this.groupCooperatedLastTime = new HashMap<>();
        this.groupCooperatedEveryTime = new HashMap<>();
    }

    /**
     * Gibt zurück ob der Agent bis zur aktuellen Runde immer kooperiert hat.
     * @return wahr falls er immer kooperiert hat, falsch sonst
     */
    public boolean getAlwaysCooperated() {
        return alwaysCooperated;
    }

    /**
     * Gibt die Punktzahl des Agenten in einem bestimmten Zyklus zurueck.
     * @param cycle Zyklus der gesuchten Punktzahl
     * @return Punktzahl
     */
    public int getScore(int cycle) {
        return score.get(cycle);
    }

    /**
     * Gibt an ob der Gegenspieler des Agenten in der letzten Runde kooperiert hat.
     * @return wahr wenn er kooperiert hat, falsch sonst
     */
    public boolean getOpponentCooperated() {
        return opponentCooperated.get(currentRound);
    }

    /**
     * Gibt an ob der Gegenspieler des Agenten in einer bestimmten Runde kooperiert hat.
     * @param round Runde der gesuchten Aktion
     * @return wahr wenn er kooperiert hat, falsch sonst
     */
    public boolean getOpponentCooperated(int round) {
        return opponentCooperated.get(round);
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
     * Gibt den Namen der Strategie des Agenten in einem bestimmten Zyklus zurueck.
     * @param cycle Zyklus der gesuchten Strategie
     * @return Name der Strategie
     */
    public String getStrategy(int cycle) {
        return strategies.get(cycle);
    }

    /**
     * Speichert die Punktzahl fuer den aktuellen Zyklus.
     * @param score aktuelle Punktzahl
     */
    public void setScore(int score){
        this.score.add(currentCycle, score);
    }

    /**
     * Speichert ob der Gegenspieler in der aktuellen Runde kooperiert hat.
     * @param cooperated ob der Gegenspieler kooperiert hat
     */
    public void setCooperated(boolean cooperated) {
        this.opponentCooperated.add(currentRound, cooperated);
    }

    /**
     * Speichert den Gegenspieler fuer die aktuelle Runde.
     * @param opponent Gegenspieler
     */
    public void setOpponent(Agent opponent) {
        this.opponent.add(currentRound, opponent);
    }

    /**
     * Speichert den Namen der Strategie für den aktuellen Zyklus.
     * @param strategy Name der Strategie
     */
    public void setStrategy(Strategy strategy) {
        this.strategies.add(currentCycle, strategy.getName());
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
        if(cooperatedLastTime.get(agent) == null){
            cooperatedLastTime.put(agent, true);
            return true;
        }else {
            return cooperatedLastTime.get(agent);
        }
    }

    /**
     * Gibt zurueck ob der Gegenspieler jedes Mal mit diesem Agenten kooperiert hat.
     * @param agent Gegenspieler
     * @return wahr, wenn er kooperiert hat, falsch sonst
     */
    public boolean cooperatedEveryTime(Agent agent) {
        if(cooperatedEveryTime.get(agent) == null){
            cooperatedEveryTime.put(agent, true);
            return true;
        }else {
            return cooperatedEveryTime.get(agent);
        }
    }

    /**
     * Gibt zurueck ob dieser Agent beim letzten Spiel gegen einen Agenten aus einer bestimmten Gruppe kooperiert hat.
     * @param group spezifische Gruppe
     * @return wahr, wenn er kooperiert hat, falsch sonst
     */
    public boolean groupCooperatedLastTime(Group group) {
        if(groupCooperatedLastTime.get(group) == null){
            groupCooperatedLastTime.put(group, true);
            return true;
        }else {
            return groupCooperatedLastTime.get(group);
        }
    }

    /**
     * Gibt zurueck ob dieser Agent bei jedem vorherigen Spiel gegen einen Agenten aus einer bestimmten Gruppe
     * kooperiert hat.
     * @param group spezifische Gruppe
     * @return wahr, wenn er kooperiert hat, falsch sonst
     */
    public boolean groupCooperatedEveryTime(Group group) {
        if(groupCooperatedEveryTime.get(group) == null){
            groupCooperatedEveryTime.put(group, true);
            return true;
        }else {
            return groupCooperatedEveryTime.get(group);
        }
    }

    /**
     * Speichert ob ein Gegenspieler im letzten Spiel gegen diesen Agenten kooperiert hat.
     * @param agent Gegenspieler
     * @param cooperated wahr wenn er kooperiert hat, falsch sonst
     */
    public void setCooperatedLastTime(Agent agent, boolean cooperated){
        if(cooperatedLastTime.containsKey(agent)) {
            cooperatedLastTime.replace(agent, cooperated);
        }else {
            cooperatedLastTime.put(agent, cooperated);
        }
    }

    /**
     * Speichert ob ein Gegenspieler in jedem vorherigen Spiel gegen diesen Agenten kooperiert hat.
     * @param agent Gegenspieler
     * @param cooperated wahr wenn er kooperiert hat, falsch sonst
     */
    public void setCooperatedEveryTime(Agent agent, boolean cooperated) {
        if(cooperatedEveryTime.containsKey(agent)) {
            cooperatedEveryTime.replace(agent, cooperated);
        }else {
            cooperatedEveryTime.put(agent, cooperated);
        }
    }

    /**
     * Speichert ob dieser Agent beim letzten Spiel gegen einen Gegenspieler einer spezifischen Gruppe
     * kooperiert hat.
     * @param group Gruppe des Gegenspielers
     * @param cooperated wahr wenn er kooperiert hat, falsch sonst
     */
    public void setGroupCooperatedLastTime(Group group, boolean cooperated) {
        if(groupCooperatedLastTime.containsKey(group)) {
            groupCooperatedLastTime.replace(group, cooperated);
        }else {
            groupCooperatedLastTime.put(group, cooperated);
        }
    }

    /**
     * Speichert ob dieser Agent bei jedem vorherigen letzten Spiel gegen einen Gegenspieler einer
     * spezifischen Gruppe kooperiert hat.
     * @param group Gruppe des Gegenspielers
     * @param cooperated wahr wenn er kooperiert hat, falsch sonst
     */
    public void setGroupCooperatedEveryTime(Group group, boolean cooperated) {
        if(groupCooperatedEveryTime.containsKey(group)) {
            groupCooperatedEveryTime.replace(group, cooperated);
        }else {
            groupCooperatedEveryTime.put(group, cooperated);
        }
    }

    /**
     * Gibt die aktuelle Rundenzahl zurück.
     * @return Rundenzahl
     */
    public int getCurrentRound() { return currentRound; }

    /**
     * Gibt die aktuelle Zykluszahl zurück.
     * @return Rundenzahl
     */
    public int getCurrentCycle() { return currentCycle; }

    /**
     * Inkrementiert den Rundenzähler um eins.
     */
    public void increaseRoundCount() {
        currentRound++;
    }

    /**
     * Inkrementiert den Zykluszähler um eins.
     */
    public void increaseCycleCount() {
        currentCycle++;
    }
}
