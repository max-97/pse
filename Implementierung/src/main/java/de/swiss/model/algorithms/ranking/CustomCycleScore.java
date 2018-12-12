package de.swiss.model.algorithms.ranking;

import de.swiss.model.Agent;
import de.swiss.model.Simulation;

import java.util.HashMap;

public class CustomCycleScore implements RankingAlgorithm {

    public static final String NAME = "";
    public static final String DESCRIPTION = "";
    private final int WINDOW_SIZE;

    public CustomCycleScore(int WINDOW_SIZE) {
        this.WINDOW_SIZE = WINDOW_SIZE;
    }

    @Override
    public HashMap<Agent, Integer> getRankings(Simulation sim) {
        return null;
    }
}
