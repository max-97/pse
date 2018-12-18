package de.sswis.model;

import java.util.HashMap;
import java.util.Observable;

public class Simulation extends Observable implements Runnable{
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
}
