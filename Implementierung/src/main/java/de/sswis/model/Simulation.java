package de.sswis.model;

import de.sswis.controller.SimulationObserver;

import java.util.HashMap;
import java.util.Observable;

public class Simulation implements Runnable{
    private Configuration config;
    private int round;
    private int cycle;
    private HashMap<Agent, Integer> currentRanking;
    private Pair[] currentPairs;
    private boolean equilibrium;

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

    public void addObserver(SimulationObserver o) {

    }

    public void notifyObservers() {

    }

    public void deleteObserver(SimulationObserver o) {

    }
}
