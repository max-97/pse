package de.sswis.model.algorithms.pairing;

import de.sswis.model.Pair;
import de.sswis.model.Simulation;

public interface PairingAlgorithm {
    Pair[] getPairing(Simulation sim);
}
