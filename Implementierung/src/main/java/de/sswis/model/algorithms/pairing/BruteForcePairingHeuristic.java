package de.sswis.model.algorithms.pairing;

import de.sswis.model.Pair;
import de.sswis.model.Simulation;

/**
 * Ein Paarungsalgorithmus der die Agenten einer Simulation so paart, dass jeder Agent mit dem
 * ersten verbleibenden Agenten gepaart wird, so dass die Distanz des Paares unter einem gewissen Schwellwert liegt.
 * Ein Agentenpaar ist umso besser, desto kleiner die Distanz zwischen den beiden Agenten ist.
 * Die Distanz liegt zwischen 0 und 1, wobei 0 fuer 100% und 1 fuer 0% Kooperationswahrscheinlichkeit steht.
 * @author Michel Bodé
 */
public class BruteForcePairingHeuristic implements PairingAlgorithm{

    @Override
    public Pair[] getPairing(Simulation sim) {
        return new Pair[0];
    }
}
