package de.swiss.model.algorithms.adaptation;

import de.swiss.model.Simulation;

public class RandomAdaptation implements AdaptationAlgorithm, MixedAdaptationAlgorithm {
    private final int PROBABILITY;

    public RandomAdaptation(int PROBABILITY) {
        this.PROBABILITY = PROBABILITY;
    }
    @Override
    public void adapt(Simulation sim) {

    }
}
