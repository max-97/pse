package de.sswis.view.model;

import java.util.List;

public class Configuration {

    private Game game;
    private Initialization init;
    private String adaptationAlg;
    private String pairingAlg;
    private String rankingAlg;
    private int rounds;
    private int cycles;
    private int cycleRoundCount;
    private double adaptationProbability;
    private List<Strategy> strategies;

    private Result result;

    public boolean isCorrect () {
        //TODO: implement me
        return false;
    }

}
