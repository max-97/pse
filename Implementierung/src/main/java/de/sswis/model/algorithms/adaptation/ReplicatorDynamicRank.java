package de.sswis.model.algorithms.adaptation;

import de.sswis.model.Simulation;

public class ReplicatorDynamicRank implements AdaptationAlgorithm{
    public static final String NAME = "";
    public static final String DESCRIPTION = "";
    private final double BETA;

    public ReplicatorDynamicRank(double BETA) {
        this.BETA = BETA;
    }
    @Override
    public void adapt(Simulation sim) {

    }
}
