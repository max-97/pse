package de.sswis.model.algorithms.adaptation;

import de.sswis.model.Agent;

/**
 * Ein Algorithmus der die Strategie eines Agenten anpasst, wenn der Agent mit dem verglichen wird
 * zu den obersten {@code PERCENTAGE} Prozent der Rangliste gehoert. Der Agent uebernimmt die Strategie des anderen,
 * falls diese Kondition erfuellt ist. Der Rang eines Agenten wird nicht angepasst, wenn der Rang des Agenten mit
 * dem verglichen wird tiefer ist.
 * @author Michel Bodé
 */
public class RankPercentage implements AdaptationAlgorithm {
    public static final String NAME = "";
    public static final String DESCRIPTION = "";
    private final int PERCENTAGE;

    /**
     * Konstruktor
     * @param PERCENTAGE Prozentsatz der angibt von welchen Agenten Stragegien uebernommen werden
     */
    public RankPercentage(int PERCENTAGE) {
        this.PERCENTAGE = PERCENTAGE;
    }

    @Override
    public void adapt(Agent[] agents, double adaptationProbability) {

    }
}
