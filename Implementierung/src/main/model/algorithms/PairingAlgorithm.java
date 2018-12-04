package model.algorithms;

import model.Pair;
import model.Simulation;

public interface PairingAlgorithm {
    public abstract Pair[] getPairing(Simulation sim);
}
