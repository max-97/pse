package de.sswis.model.algorithms.ranking;

import de.sswis.model.Agent;
import de.sswis.model.Simulation;

import java.util.HashMap;

public interface RankingAlgorithm {
    HashMap<Agent, Integer> getRankings(Simulation sim);
}
