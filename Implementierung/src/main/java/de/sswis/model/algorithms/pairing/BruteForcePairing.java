package de.sswis.model.algorithms.pairing;

import de.sswis.model.Agent;
import de.sswis.model.Game;
import de.sswis.model.Pair;

/**
 * Ein Paarungsalgorithmus der die Agenten einer Simulation so paart, dass jeder Agent mit dem
 * bestmoeglichen verbleibenden Agenten gepaart wird. Ein Agentenpaar ist umso besser, desto kleiner die
 * Distanz zwischen den beiden Agenten ist. Die Distanz liegt zwischen 0 und 1, wobei 0 fuer 100%
 * und 1 fuer 0% Kooperationswahrscheinlichkeit steht.
 * @author Michel Bodé
 */
public class BruteForcePairing implements PairingAlgorithm{
    public static final int PARAMETER_COUNT = 0;
    public static final String[] PARAMETER_NAMES = {};

    @Override
    public Pair[] getPairing(Agent[] agents, Game game) {
        return new Pair[0];
    }
}
