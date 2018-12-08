package de.swiss.model.algorithms.pairing;

import de.swiss.model.Pair;
import de.swiss.model.Simulation;

public interface PairingAlgorithm {
    public abstract Pair[] getPairing(Simulation sim);
}
