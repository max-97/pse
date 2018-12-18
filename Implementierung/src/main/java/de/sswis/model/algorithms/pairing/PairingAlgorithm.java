package de.sswis.model.algorithms.pairing;

import de.sswis.model.Pair;
import de.sswis.model.Simulation;

/**
 * Ein Algorithmus zum Bilden von Paaren von Agenten einer Simulation.
 *
 * @author Michel Bodé
 */
public interface PairingAlgorithm {
    /**
     * Paart die Agenten einer Simulation entsprechend des Algorithmus.
     * @param sim die Simulation deren Agenten gepaart werden sollen
     * @return eine Menge von Agent-Paaren
     */
    Pair[] getPairing(Simulation sim);
}
