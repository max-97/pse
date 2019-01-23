package de.sswis.model.algorithms.pairing;


import de.sswis.model.Agent;
import de.sswis.model.Game;
import de.sswis.model.Pair;
import org.jgrapht.Graph;
import org.jgrapht.alg.interfaces.MatchingAlgorithm;
import org.jgrapht.alg.matching.blossom.v5.KolmogorovMinimumWeightPerfectMatching;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.DefaultUndirectedWeightedGraph;

import java.util.Iterator;

/**
 * Ein Paarungsalgorithmus der die Agenten einer Simulation so paart, dass die Anzahl an kooperierenden Paaren
 * maximal ist. Der Algorithmus erstellt dabei einen vollstaendigen Graphen mit Agenten als Knoten und dem inversen
 * der Kooperationswahrscheinlichkeiten der Agenten als Kantengewichten und berechnet darauf ein Matching
 * mit minimalem Gewicht.
 * @author Michel Bodé
 */
public class MinimumWeightMatching implements PairingAlgorithm {
    public static final int PARAMETER_COUNT = 0;
    public static final String[] PARAMETER_NAMES = {};

    @Override
    public Pair[] getPairing(Agent[] agents, Game game) {
        Pair[] result = new Pair[agents.length/2];
        Graph<Agent, DefaultEdge> agentGraph = agentsToGraph(agents);
        KolmogorovMinimumWeightPerfectMatching<Agent, DefaultEdge> alg = new KolmogorovMinimumWeightPerfectMatching<>(agentGraph);
        MatchingAlgorithm.Matching<Agent, DefaultEdge> matching = alg.getMatching();
        Iterator<DefaultEdge> it = matching.iterator();

        int count = 0;
        while (it.hasNext()) {
            DefaultEdge currentEdge = it.next();
            result[count++] = new Pair(agentGraph.getEdgeSource(currentEdge), agentGraph.getEdgeTarget(currentEdge));
        }
        return result;
    }

    private Graph<Agent, DefaultEdge> agentsToGraph(Agent[] agents) {
        Graph<Agent, DefaultEdge> agentGraph = new DefaultUndirectedWeightedGraph<>(null, null);

        for(int i = 0; i < agents.length; i++) {
            agentGraph.addVertex(agents[i]);
        }

        for(int i = 0; i < agents.length - 1; i++) {
            for(int j = i + 1; j < agents.length; j++) {
                agentGraph.addEdge(agents[i], agents[j]);
                agentGraph.setEdgeWeight(agents[i], agents[j], calculateDistance(agents[i], agents[j]));
            }
        }
        return agentGraph;
    }

    private double calculateDistance(Agent agent1, Agent agent2) {
        //TODO
        return 0;
    }
}
