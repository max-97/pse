package de.sswis.model.algorithms.pairing;


import de.sswis.model.Agent;
import de.sswis.model.Pair;
import de.sswis.model.Simulation;
import org.jgrapht.Graph;
import org.jgrapht.alg.interfaces.MatchingAlgorithm;
import org.jgrapht.graph.DefaultEdge;

/**
 * Ein Paarungsalgorithmus der die Agenten einer Simulation so paart, dass die Anzahl an kooperierenden Paaren
 * maximal ist. Der Algorithmus erstellt dabei einen vollstaendigen Graphen mit Agenten als Knoten und den
 * Kooperationswahrscheinlichkeiten der Agenten als Kantengewichten und berechnet darauf ein perfektes Matching
 * mit maximalem Gewicht.
 * @author Michel Bodé
 */
public class MaximumWeightMatching implements PairingAlgorithm {

    @Override
    public Pair[] getPairing(Simulation sim) {
        return new Pair[0];
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
