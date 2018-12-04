package model.algorithms.adaptation;

import model.Simulation;

public class ReplicatorDynamicScore implements AdaptationAlgorithm{
    public static final String NAME = "";
    public static final String DESCRIPTION = "";
    public final double BETA;

    public ReplicatorDynamicScore(double BETA) {
        this.BETA = BETA;
    }
    @Override
    public void adapt(Simulation sim) {

    }
}
