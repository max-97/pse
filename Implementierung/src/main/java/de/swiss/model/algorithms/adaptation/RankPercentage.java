package de.swiss.model.algorithms.adaptation;

import de.swiss.model.Simulation;

public class RankPercentage implements AdaptationAlgorithm {
    public static final String NAME = "";
    public static final String DESCRIPTION = "";
    private final int PERCENTAGE;

    public RankPercentage(int PERCENTAGE) {
        this.PERCENTAGE = PERCENTAGE;
    }

    @Override
    public void adapt(Simulation sim) {

    }
}
