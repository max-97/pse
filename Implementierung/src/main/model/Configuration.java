package model;

public class Configuration {
    private Game game;
    private Initialization init;
    private AdaptationAlgorithm adaptationAlg;
    private PairingAlgorithm pairingAlg;
    private RankingAlgorithm rankingAlg;
    private int rounds;
    private int cycles;
    private int cycleRoundCount;
    private int repetitions;
    private double adaptationProbability;

    public Configuration(Game game, AdaptationAlgorithm adaptation, PairingAlgorithm pairing, RankingAlgorithm rangking,
                         int rounds, int cycles, int cycleRoundCount, int repetitions, double adaptationProbability) {

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

    public int getRepetitions() {
        return repetitions;
    }

    public double getAdaptationProbability() {
        return adaptationProbability;
    }

    public Simulation simulate() {
        return new Simulation(this);
    }

}
