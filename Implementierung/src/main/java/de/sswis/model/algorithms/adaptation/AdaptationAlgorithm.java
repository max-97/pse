package de.sswis.model.algorithms.adaptation;

import de.sswis.model.Agent;

/**
 * Ein Algorithmus zum Anpassen der Strategien von Agenten einer Simulation.
 * Jeder Agent A wird mit einem zufaelligen Agenten verglichen, ist letzterer erfolgreicher,
 * so wird die Strategie von A durch den Algorithmus angepasst.
 * @author Michel Bodé
 */
public interface AdaptationAlgorithm {
    /**
     * Passt die Strategien der Agenten entsprechend des Algorithmus an.
     * @param agents Menge an Agenten deren Strategie angepasst werden soll
     * @param adaptationProbability Wahrscheinlichkeit der Strategieanpassung
     */
    void adapt(Agent[] agents, double adaptationProbability);
}
