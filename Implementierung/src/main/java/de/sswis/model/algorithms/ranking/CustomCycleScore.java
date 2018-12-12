package de.sswis.model.algorithms.ranking;

import de.sswis.model.Agent;
import de.sswis.model.Simulation;

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
