package de.swiss.model;

import java.util.HashMap;
import java.util.Observable;

public class Simulation extends Observable {
    private Configuration config;
    private int round;
    private int cycle;
    private HashMap<Agent, Integer> currentRanking;

    public Simulation(Configuration config) {

    }

    public void simulateRound() {

    }

    public HashMap<Agent, Integer> getCurrentRanking() {
        return currentRanking;
    }


}
