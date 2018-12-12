package de.sswis.model;

import java.util.HashMap;
import java.util.Observable;

public class Simulation extends Observable {
    private Configuration config;
    private int round;
    private int cycle;
    private HashMap<Agent, Integer> currentRanking;
    private Pair[] currentPairs;

    public Simulation(Configuration config) {

    }

    public void simulateRound() {

    }

    public HashMap<Agent, Integer> getCurrentRanking() {
        return currentRanking;
    }

    public Pair[] getCurrentPairs() { return currentPairs;}

    public void saveResults() {

    }

    public void restart() {

    }

    public void abort() {

    }
}
