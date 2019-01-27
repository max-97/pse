package de.sswis.model.algorithms.pairing;

import de.sswis.model.*;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;

/**
 * Ein Paarungsalgorithmus der die Agenten einer Simulation so paart, dass jeder Agent mit dem
 * bestmoeglichen verbleibenden Agenten gepaart wird. Ein Agentenpaar ist umso besser, desto kleiner die
 * Distanz zwischen den beiden Agenten ist. Die Distanz liegt zwischen 0 und 1, wobei 0 fuer 100%
 * und 1 fuer 0% Kooperationswahrscheinlichkeit steht.
 * @author Michel Bodé
 */
public class BruteForcePairing implements PairingAlgorithm{
    public static final int PARAMETER_COUNT = 0;
    public static final String[] PARAMETER_NAMES = {};

    @Override
    public Pair[] getPairing(Agent[] agents, Game game) {
        HashSet<Agent> agentSet = new HashSet<>(Arrays.asList(agents));
        Pair[] pairs = new Pair[agentSet.size()/2];

        for(int i = 0; i < agents.length/2; i++) {
            Iterator<Agent> it = agentSet.iterator();
            Agent agent1 = it.next();
            double smallestDistance = 2;
            Agent bestPartner = null;

            while(it.hasNext() && smallestDistance != 0) {
                Agent currentAgent = it.next();
                double distance = 1 - (calculateCoopProbability(agent1, currentAgent) *
                        calculateCoopProbability(currentAgent, agent1));
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

    private double calculateCoopProbability(Agent agent1, Agent agent2) {
        Strategy strategy = agent1.getStrategy();
        double probability = 0;

        if(strategy instanceof CombinedStrategy) {
            if(strategy.calculateAction(agent1, agent2) == Action.COOPERATION){
                return 1;
            } else {
                return 0;
            }
        } else {
            CombinedStrategy[] combinedStrategies = ((MixedStrategy)strategy).getCombinedStrategies();
            double[] probabilities = ((MixedStrategy)strategy).getProbabilities();

            for(int i = 0; i < combinedStrategies.length; i++) {
                if(combinedStrategies[i].calculateAction(agent1, agent2) == Action.COOPERATION){
                    probability += probabilities[i];
                }
            }
        }
        return probability;
    }

    @Override
    public String getName() {
        return null;
    }
}
