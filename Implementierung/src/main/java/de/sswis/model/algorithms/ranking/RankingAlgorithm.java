package de.sswis.model.algorithms.ranking;

import de.sswis.model.Agent;
import de.sswis.model.Simulation;

import java.util.HashMap;

public interface RankingAlgorithm {
    public HashMap<Agent, Integer> getRankings(Simulation sim);
}
