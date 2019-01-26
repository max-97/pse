package de.sswis.model.algorithms.pairing;


import de.sswis.model.Agent;
import de.sswis.model.Game;
import de.sswis.model.Pair;
import org.jgrapht.Graph;
import org.jgrapht.alg.interfaces.MatchingAlgorithm;
import org.jgrapht.graph.DefaultEdge;

/**
 * Ein Paarungsalgorithmus der die Agenten einer Simulation so paart, dass die Anzahl an kooperierenden Paaren
 * maximal ist. Der Algorithmus erstellt dabei einen vollstaendigen Graphen mit Agenten als Knoten und den
 * Kooperationswahrscheinlichkeiten der Agenten als Kantengewichten und berechnet darauf ein Matching
 * mit maximalem Gewicht.
 * @author Michel Bodé
 */
public class MaximumWeightMatching implements PairingAlgorithm {
    public static final int PARAMETER_COUNT = 0;
    public static final String[] PARAMETER_NAMES = {};

    @Override
    public Pair[] getPairing(Agent[] agents, Game game) {
        return new Pair[0];
    }

    @Override
    public String getName() {
        return null;
    }

    /*
    private Graph<Agent, DefaultEdge> agentsToGraph() { return null; }
    */

    /*
    private Pair[] matchingToAgentPairs() { return null; }
    */

    /*
    private MatchingAlgorithm.Matching getMatching() { return null; }
    */
}
