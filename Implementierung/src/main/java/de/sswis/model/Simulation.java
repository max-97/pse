package de.sswis.model;

import de.sswis.controller.SimulationObserver;

import java.util.HashMap;
import java.util.List;
/**
 * Eine Simulation basierend auf einer Konfiguration, welche gestartet werden kann und nach Beenden
 * Ergebnisse zurückliefern kann. Die Simulation stoppt wenn es zu einem Gleichgewicht kommt oder
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
     * Gibt das aktuelle Ranking der Agenten zurück.
     * @return Ranking der Agenten
     */
    public HashMap<Agent, Integer> getCurrentRanking() {
        return currentRanking;
    }

    /**
     * Gibt die aktuellen Paare zurück.
     * @return Menge an Agentpaaren
     */
    public Pair[] getCurrentPairs() { return currentPairs;}

    /**
     * Gibt die Agenten nach Beendigung der Simulation zurück, welche alle Daten bezüglich den Ergebnissen
     * und dem Verlauf der Simulation enthalten.
     * @return Menge an Agenten
     */
    public Agent[] getResults() {
        return null;
    }

    /**
     * Gibt zurück ob ein Gleichgewicht erreicht wurde.
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
