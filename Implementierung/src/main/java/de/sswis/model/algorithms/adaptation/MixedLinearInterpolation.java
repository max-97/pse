package de.sswis.model.algorithms.adaptation;

import de.sswis.model.Agent;

import java.util.HashMap;

/**
 * Ein Algorithmus der die Wahrscheinlichkeiten der gemischten Strategien der Agenten einer Simulation
 * durch lineare Interpolation anpasst. Die Wahrscheinlichkeiten werden staerker angepasst desto groesser
 * die Differenz der Gesamtpunktzahlen der verglichenen Agenten ist.
 * @author Michel Bodé
 */
public class MixedLinearInterpolation implements AdaptationAlgorithm{

    public static final String NAME = "";
    public static final String DESCRIPTION = "";
    public static final int PARAMETER_COUNT = 0;
    public static final String[] PARAMETER_NAMES = {};

    @Override
    public void adapt(Agent[] agents, HashMap<Agent, Integer> currentRanking, double adaptationProbability) {

    }

    @Override
    public String getName() {
        return null;
    }

    @Override
    public void setParameters(HashMap<String, Object> parameters) {

    }
}
