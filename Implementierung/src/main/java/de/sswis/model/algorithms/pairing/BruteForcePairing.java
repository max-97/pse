package de.sswis.model.algorithms.pairing;

import de.sswis.model.Pair;
import de.sswis.model.Simulation;

/**
 * Ein Paarungsalgorithmus der die Agenten einer Simulation so paart, dass jeder Agent mit dem
 * bestmöglichen verbleibenden Agenten gepaart wird. Ein Agentenpaar ist umso besser, desto kleiner die
 * Distanz zwischen den beiden Agenten ist. Die Distanz liegt zwischen 0 und 1, wobei 0 für 100%
 * und 1 für 0% Kooperationswahrscheinlichkeit steht.
 * @author Michel Bodé
 */
public class BruteForcePairing implements PairingAlgorithm{

    @Override
    public Pair[] getPairing(Simulation sim) {
        return new Pair[0];
    }
}
