package de.sswis.model;

import de.sswis.controller.SimulationObserver;

import java.util.HashMap;
import java.util.List;
/**
 * Eine Simulation basierend auf einer Konfiguration, welche gestartet werden kann und nach Beenden
 * Ergebnisse zurueckliefern kann. Die Simulation stoppt wenn es zu einem Gleichgewicht kommt oder
 * die maximale Rundenzahl erreicht wird.
 * @author Michel Bodé
 */
public class Simulation implements Runnable, ObservableSimulation {
    private Configuration config;
    private int round;
    private int cycle;
    private HashMap<Agent, Integer> currentRanking;
    private Pair[] currentPairs;
    private boolean equilibrium;
    private List<SimulationObserver> observers;

    /**
     * Erstellt eine Simulation.
     * @param config zugrunde liegende Konfiguration
     */
    public Simulation(Configuration config) {

    }

    /**
     * Startet die Simulation.
     */
    public void simulate() {

    }

    /**
     * Gibt das aktuelle Ranking der Agenten zurueck.
     * @return Ranking der Agenten
     */
    public HashMap<Agent, Integer> getCurrentRanking() {
        return currentRanking;
    }

    /**
     * Gibt die aktuellen Paare zurueck.
     * @return Menge an Agentpaaren
     */
    public Pair[] getCurrentPairs() { return currentPairs;}

    /**
     * Gibt die Agenten nach Beendigung der Simulation zurueck, welche alle Daten bezueglich den Ergebnissen
     * und dem Verlauf der Simulation enthalten.
     * @return Menge an Agenten
     */
    public Agent[] getResults() {
        return null;
    }

    /**
     * Gibt zurueck ob ein Gleichgewicht erreicht wurde.
     * @return wahr, wenn ein Gleichgewicht erreicht wurde, falsch sonst
     */
    public boolean EquilibriumAchieved() {
        return  equilibrium;
    }

    /**
     * Startet die Simulation neu.
     */
    public void restart() {

    }

    /**
     * Bricht die Simulation ab.
     */
    public void abort() {

    }

    /**
     * Liefert den Namen der Simulation zurück. Der Name ist identisch zum Namen der zugehörigen
     * {@code Configuration}.
     * @return der Name der Simulation
     * @see Configuration#getName()
     */
    public String getName() {
        return this.config.getName();
    }

    @Override
    public void run() {

    }

    @Override
    public void addObserver(SimulationObserver o) {

    }

    @Override
    public void notifyObservers() {

    }

    @Override
    public void deleteObserver(SimulationObserver o) {

    }
}
