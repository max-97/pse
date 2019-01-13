package de.sswis.model.algorithms.adaptation;

import de.sswis.model.Agent;

/**
 * Ein Algorithmus der die Strategien der Agenten einer Simulation mit einer gewissen Wahrscheinlichkeit zu
 * einer zufaelligen neuen Strategie anpasst. Die neue Strategie muss in der Konfiguration enthalten sein.
 * Hat der Agent eine gemischte Strategie, so wird die Wahrscheinlichkeit der neuen zufaelligen Strategie erhoeht
 * und anschliessend alle Wahrscheinlichkeiten normiert, so dass ihre Summe wieder 1 ergibt.
 * @author Michel Bodé
 */
public class RandomAdaptation implements AdaptationAlgorithm {

    public static final String NAME = "";
    public static final String DESCRIPTION = "";
    private final int PROBABILITY;

    /**
     * Konstruktor
     * @param PROBABILITY Wahrscheinlichkeit mit der eine neue Strategie gewaehlt wird
     */
    public RandomAdaptation(int PROBABILITY) {
        this.PROBABILITY = PROBABILITY;
    }

    @Override
    public void adapt(Agent[] agents, double adaptationProbability) {

    }
}
