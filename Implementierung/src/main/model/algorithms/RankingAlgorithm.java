package model.algorithms;

import model.Agent;
import model.Simulation;

import java.util.HashMap;

public interface RankingAlgorithm {
    public abstract HashMap<Agent, Integer> getRankings(Simulation sim);
}
