package de.sswis.model.algorithms.pairing;

import de.sswis.model.Agent;
import de.sswis.model.Game;
import de.sswis.model.Pair;

import java.util.HashSet;
import java.util.Iterator;

/**
 * Ein Paarungsalgorithmus der die Agenten einer Simulation so paart, dass jeder Agent mit dem
 * ersten verbleibenden Agenten gepaart wird, so dass die Distanz des Paares unter einem gewissen Schwellwert liegt.
 * Ein Agentenpaar ist umso besser, desto kleiner die Distanz zwischen den beiden Agenten ist.
 * Die Distanz liegt zwischen 0 und 1, wobei 0 fuer 100% und 1 fuer 0% Kooperationswahrscheinlichkeit steht.
 * @author Michel Bodé
 */
public class BruteForcePairingHeuristic implements PairingAlgorithm{
    public static final int PARAMETER_COUNT = 1;
    public static final String[] PARAMETER_NAMES = {"Threshold"};
    private final double THRESHOLD;

    public BruteForcePairingHeuristic(double THRESHOLD) {
        this.THRESHOLD = THRESHOLD;
    }

    @Override
    public Pair[] getPairing(Agent[] agents, Game game) {
        HashSet<Agent> agentSet = new HashSet<>();

        for(int i = 0; i < agents.length; i++) {
            agentSet.add(agents[i]);
        }

        Pair[] pairs = new Pair[agentSet.size()/2];

        for(int i = 0; i < agents.length/2; i++) {
            Iterator<Agent> it = agentSet.iterator();
            Agent agent1 = it.next();
            double smallestDistance = 2;
            Agent bestPartner = null;
            while(it.hasNext() && smallestDistance > THRESHOLD) {
                Agent currentAgent = it.next();
                double distance = calculateDistance(agent1, currentAgent);
                if(distance < smallestDistance) {
                    smallestDistance = distance;
                    bestPartner = currentAgent;
                }
            }
            pairs[i] = new Pair(agent1, bestPartner);
            agentSet.remove(agent1);
            agentSet.remove(bestPartner);
        }
        return pairs;
    }

    private double calculateDistance(Agent agent1, Agent agent2) {
        //TODO
        return 0;
    }
}
