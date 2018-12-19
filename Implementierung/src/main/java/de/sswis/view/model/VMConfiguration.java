package de.sswis.view.model;

import java.util.List;

public class VMConfiguration {

    private VMGame game;
    private VMInitialization init;
    private String adaptationAlg;
    private String pairingAlg;
    private String rankingAlg;
    private int rounds;
    private int cycles;
    private int cycleRoundCount;
    private double adaptationProbability;
    private List<VMStrategy> strategies;

    private VMResult result;

    public boolean isCorrect () {
        //TODO: implement me
        return false;
    }

}
