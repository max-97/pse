package de.sswis.model.algorithms.pairing;

import de.sswis.model.Agent;
import de.sswis.model.Game;
import de.sswis.model.Pair;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Random;

/**
 * Ein Paarungsalgorithmus der die Agenten einer Simulation zufaellig miteinander paart.
 * @author Michel Bodé
 */
public class RandomPairing implements PairingAlgorithm {
    public static final int PARAMETER_COUNT = 0;
    public static final String[] PARAMETER_NAMES = {};

    @Override
    public Pair[] getPairing(Agent[] agents, Game game) {
        Agent[] agentsArray = agents.clone();
        shuffle(agentsArray);
        HashSet<Agent> agentSet = new HashSet<>(Arrays.asList(agentsArray));

        Iterator<Agent> it = agentSet.iterator();
        int count = 0;
        Pair[] pairs = new Pair[agentSet.size()/2];

        while(it.hasNext()) {
            Agent agent1 = it.next();
            Agent agent2 = it.next();
            agentSet.remove(agent1);
            agentSet.remove(agent2);
            pairs[count++] = new Pair(agent1, agent2);
        }
        return pairs;
    }

    private void shuffle(Agent[] agents) {
        Random rnd = new Random();
        for (int i = agents.length - 1; i > 0; i--)
        {
            int index = rnd.nextInt(i + 1);
            Agent a = agents[index];
            agents[index] = agents[i];
            agents[i] = a;
        }
    }

}
