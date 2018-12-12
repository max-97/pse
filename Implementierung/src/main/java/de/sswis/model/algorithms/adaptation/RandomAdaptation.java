package de.sswis.model.algorithms.adaptation;

import de.sswis.model.Simulation;

public class RandomAdaptation implements AdaptationAlgorithm, MixedAdaptationAlgorithm {

    public static final String NAME = "";
    public static final String DESCRIPTION = "";
    private final int PROBABILITY;

    public RandomAdaptation(int PROBABILITY) {
        this.PROBABILITY = PROBABILITY;
    }
    @Override
    public void adapt(Simulation sim) {

    }
}
