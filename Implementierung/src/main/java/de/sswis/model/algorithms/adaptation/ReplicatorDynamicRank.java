package de.sswis.model.algorithms.adaptation;

import de.sswis.model.Simulation;

/**
 * Ein Algorithmus der die Strategie eines Agenten einer Simulation anpasst, in Abhängigkeit von der Differenz
 * der Ränge zweier verglichener Agenten. Ein Agent übernimmt die Strategie eines anderen mit einer Wahrscheinlichkeit
 * δ*β, wobei δ die nichtnegative Differenz der Ränge ist und β eine Konstante, so dass 0 ≤ δ*β ≤ 1 gilt.
 * @author Michel Bodé
 */
public class ReplicatorDynamicRank implements AdaptationAlgorithm {
    public static final String NAME = "";
    public static final String DESCRIPTION = "";
    private final double BETA;

    /**
     * Konstruktor
     * @param BETA Konstante β, so dass 0 ≤ δ*β ≤ 1 gilt
     */
    public ReplicatorDynamicRank(double BETA) {
        this.BETA = BETA;
    }

    @Override
    public void adapt(Simulation sim) {

    }
}
