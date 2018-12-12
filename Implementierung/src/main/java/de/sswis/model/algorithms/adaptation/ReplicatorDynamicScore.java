package de.sswis.model.algorithms.adaptation;

import de.sswis.model.Simulation;

public class ReplicatorDynamicScore implements AdaptationAlgorithm{
    public static final String NAME = "";
    public static final String DESCRIPTION = "";
    private final double BETA;

    public ReplicatorDynamicScore(double BETA) {
        this.BETA = BETA;
    }
    @Override
    public void adapt(Simulation sim) {

    }
}
