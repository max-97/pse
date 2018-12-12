package de.sswis.model;

import de.sswis.model.algorithms.adaptation.AdaptationAlgorithm;
import de.sswis.model.algorithms.adaptation.MixedAdaptationAlgorithm;
import de.sswis.model.algorithms.pairing.PairingAlgorithm;
import de.sswis.model.algorithms.ranking.RankingAlgorithm;

import java.util.List;

public class Configuration {
    private Game game;
    private Initialization init;
    private AdaptationAlgorithm adaptationAlg;
    private MixedAdaptationAlgorithm mixedAdaptationAlg;
    private PairingAlgorithm pairingAlg;
    private RankingAlgorithm rankingAlg;
    private int rounds;
    private int cycles;
    private int cycleRoundCount;
    private double adaptationProbability;
    private boolean mixedStrategies;
    private List<Strategy> strategies;

    public Configuration(Game game, AdaptationAlgorithm adaptation, PairingAlgorithm pairing, RankingAlgorithm rangking,
                         int rounds, int cycles, int cycleRoundCount, double adaptationProbability) {

    }

    public Configuration(Game game, AdaptationAlgorithm adaptation, MixedAdaptationAlgorithm mixedAdaptation,
                         PairingAlgorithm pairing, RankingAlgorithm rangking, int rounds, int cycles,
                         int cycleRoundCount, double adaptationProbability) {

    }

    public Game getGame() {
        return game;
    }

    public Initialization getInit(){
        return init;
    }


    public AdaptationAlgorithm getAdaptationAlg(){
        return adaptationAlg;
    }

    public MixedAdaptationAlgorithm getMixedAdaptationAlg(){
        return mixedAdaptationAlg;
    }

    public PairingAlgorithm getPairingAlg() {
        return pairingAlg;
    }

    public int getRounds() {
        return rounds;
    }

    public int getCycles() {
        return cycles;
    }

    public int getCycleRoundCount() {
        return cycleRoundCount;
    }

    public double getAdaptationProbability() {
        return adaptationProbability;
    }

    public Simulation simulate(int repetitions) {
        return new Simulation(this);
    }

    public boolean hasMixedStrategies() { return mixedStrategies;}

    public void saveConfiguration() {

    }

    public List<Strategy> getAllStrategies() {
        return strategies;
    }

    public void addStrategy(Strategy newStrategy) {

    }

}
