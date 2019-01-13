package de.sswis.model.algorithms.pairing;

import de.sswis.model.Agent;
import de.sswis.model.Game;
import de.sswis.model.Pair;

/**
 * Ein Paarungsalgorithmus der die Agenten einer Simulation zufaellig miteinander paart.
 * @author Michel Bodé
 */
public class RandomPairing implements PairingAlgorithm {

    @Override
    public Pair[] getPairing(Agent[] agents, Game game) {
        return new Pair[0];
    }
}
