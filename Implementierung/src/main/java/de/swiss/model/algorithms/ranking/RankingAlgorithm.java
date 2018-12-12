package de.swiss.model.algorithms.ranking;

import de.swiss.model.Agent;
import de.swiss.model.Simulation;

import java.util.HashMap;

public interface RankingAlgorithm {
    public HashMap<Agent, Integer> getRankings(Simulation sim);
}
