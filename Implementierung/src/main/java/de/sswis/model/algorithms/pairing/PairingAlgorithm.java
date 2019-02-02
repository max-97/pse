package de.sswis.model.algorithms.pairing;

import de.sswis.model.Agent;
import de.sswis.model.Game;
import de.sswis.model.Pair;

import java.util.HashMap;

/**
 * Ein Algorithmus zum Bilden von Paaren von Agenten einer Simulation.
 *
 * @author Michel Bodé
 */
public interface PairingAlgorithm {
    /**
     * Paart die Agenten einer Simulation entsprechend des Algorithmus.
     * @param agents Menge an Agenten die gepaart werden sollen
     * @param game Spiel für das Paare gebildetet werden sollen
     * @return eine Menge von Agent-Paaren
     */
    Pair[] getPairing(Agent[] agents, Game game);

    String getName();

	void setParameters(HashMap<String, Object> parameters);

    String[] getParameters();
}
