package de.sswis.model.algorithms.adaptation;

import de.sswis.model.Agent;

/**
 * Ein Algorithmus der die Wahrscheinlichkeiten der gemischten Strategien der Agenten einer Simulation
 * durch lineare Interpolation anpasst. Die Wahrscheinlichkeiten werden staerker angepasst desto groesser
 * die Differenz der Gesamtpunktzahlen der verglichenen Agenten ist.
 * @author Michel Bodé
 */
public class MixedLinearInterpolation implements AdaptationAlgorithm{

    public static final String NAME = "";
    public static final String DESCRIPTION = "";

    @Override
    public void adapt(Agent[] agents, double adaptationProbability) {

    }
}
