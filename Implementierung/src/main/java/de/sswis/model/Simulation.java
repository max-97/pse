package de.sswis.model;

import de.sswis.controller.SimulationObserver;

import java.util.HashMap;
import java.util.List;

public class Simulation implements Runnable, ObservableSimulation {
    private Configuration config;
    private int round;
    private int cycle;
    private HashMap<Agent, Integer> currentRanking;
    private Pair[] currentPairs;
    private boolean equilibrium;
    private List<SimulationObserver> observers;

    public Simulation(Configuration config) {

    }

    public void simulate() {

    }

    public HashMap<Agent, Integer> getCurrentRanking() {
        return currentRanking;
    }

    public Pair[] getCurrentPairs() { return currentPairs;}

    public Agent[] getResults() {
        return null;
    }

    public boolean EquilibriumAchieved() {
        return  equilibrium;
    }

    public void restart() {

    }

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
