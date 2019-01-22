package de.sswis.model.algorithms.adaptation;

import de.sswis.model.Agent;

import java.util.HashMap;

/**
 * Ein Algorithmus der die Wahrscheinlichkeiten der gemischten Strategien der Agenten einer Simulation
 * durch Summenbildung und anschliessende Normierung anpasst. Die Wahrscheinlichkeiten der beiden gemischten
 * Strategien werden addiert und anschliessend normiert, so dass die Summe der neuen Wahrscheinlichkeiten
 * wieder 1 ergibt.
 * @author Michel Bodé
 */
public class MixedSum implements AdaptationAlgorithm{

    public static final String NAME = "";
    public static final String DESCRIPTION = "";
    public static final int PARAMETER_COUNT = 0;
    public static final String[] PARAMETER_NAMES = {};

    @Override
    public void adapt(Agent[] agents, HashMap<Agent, Integer> currentRanking, double adaptationProbability) {

    }
}
