package de.sswis.model.algorithms.pairing;

import de.sswis.model.Pair;
import de.sswis.model.Simulation;

/**
 * Ein Paarungsalgorithmus der die Agenten einer Simulation zufällig miteinander paart.
 * @author Michel Bodé
 */
public class RandomPairing implements PairingAlgorithm {

    @Override
    public Pair[] getPairing(Simulation sim) {
        return new Pair[0];
    }
}
