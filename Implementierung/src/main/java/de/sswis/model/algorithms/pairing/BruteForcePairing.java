package de.sswis.model.algorithms.pairing;

import de.sswis.model.Pair;
import de.sswis.model.Simulation;

/**
 * Ein Paarungsalgorithmus der die Agenten einer Simulation so paart, dass jeder Agent mit dem
 * bestmoeglichen verbleibenden Agenten gepaart wird. Ein Agentenpaar ist umso besser, desto kleiner die
 * Distanz zwischen den beiden Agenten ist. Die Distanz liegt zwischen 0 und 1, wobei 0 fuer 100%
 * und 1 fuer 0% Kooperationswahrscheinlichkeit steht.
 * @author Michel Bodé
 */
public class BruteForcePairing implements PairingAlgorithm{

    @Override
    public Pair[] getPairing(Simulation sim) {
        return new Pair[0];
    }
}
