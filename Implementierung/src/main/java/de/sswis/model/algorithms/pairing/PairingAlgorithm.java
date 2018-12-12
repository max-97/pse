package de.sswis.model.algorithms.pairing;

import de.sswis.model.Pair;
import de.sswis.model.Simulation;

public interface PairingAlgorithm {
    public Pair[] getPairing(Simulation sim);
}
