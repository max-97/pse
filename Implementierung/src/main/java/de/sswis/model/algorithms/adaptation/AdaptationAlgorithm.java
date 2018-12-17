package de.sswis.model.algorithms.adaptation;

import de.sswis.model.Simulation;

/**
 * Ein Algorithmus zum Anpassen der Strategien von Agenten einer Simulation.
 * Jeder Agent A wird mit einem zufälligen Agenten verglichen, ist letzterer erfolgreicher,
 * so wird die Strategie von A durch den Algorithmus angepasst.
 * @author Michel Bodé
 */
public interface AdaptationAlgorithm {
    /**
     * Passt die Strategien der Agenten entsprechend des Algorithmus an.
     * @param sim die Simulation in der die Strategien der Ageten angepasst werden sollen
     */
    void adapt(Simulation sim);
}
