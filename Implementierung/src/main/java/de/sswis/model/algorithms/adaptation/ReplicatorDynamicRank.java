package de.sswis.model.algorithms.adaptation;

import de.sswis.model.Simulation;

/**
 * Ein Algorithmus der die Strategie eines Agenten einer Simulation anpasst, in Abhaengigkeit von der Differenz
 * der Raenge zweier verglichener Agenten. Ein Agent uebernimmt die Strategie eines anderen mit einer Wahrscheinlichkeit
 * delta*beta, wobei delta die nichtnegative Differenz der Raenge ist und beta eine Konstante,
 * so dass delta*beta zwischen 0 und 1 liegt.
 * @author Michel Bodé
 */
public class ReplicatorDynamicRank implements AdaptationAlgorithm {
    public static final String NAME = "";
    public static final String DESCRIPTION = "";
    private final double BETA;

    /**
     * Konstruktor
     * @param BETA Konstante beta, so dass delta*beta zwischen 0 und 1 liegt
     */
    public ReplicatorDynamicRank(double BETA) {
        this.BETA = BETA;
    }

    @Override
    public void adapt(Simulation sim) {

    }
}
