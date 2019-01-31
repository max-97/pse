package de.sswis.model.algorithms.pairing;

import de.sswis.model.Agent;
import de.sswis.model.Game;
import de.sswis.model.Pair;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Random;

import java.util.HashMap;

/**
 * Ein Paarungsalgorithmus der die Agenten einer Simulation zufaellig miteinander paart.
 * @author Michel Bodé
 */
public class RandomPairing implements PairingAlgorithm {

    public static final String NAME = "Zufällige Paarung";
    public static final int PARAMETER_COUNT = 0;
    public static final String[] PARAMETER_NAMES = {};

    public RandomPairing() {}

    @Override
    public Pair[] getPairing(Agent[] agents, Game game) {
        Agent[] agentsArray = agents.clone();
        shuffle(agentsArray);
        Pair[] pairs = new Pair[agents.length/2];

        for(int i = 0; i < pairs.length; i++) {
            pairs[i] = new Pair(agents[i], agents[agents.length - 1 - i]);
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


    @Override
    public String getName() {
        return NAME;
    }

    @Override
    public void setParameters(HashMap<String, Object> parameters) {

    }
    @Override
    public String[] getParameters() {
        return PARAMETER_NAMES;
    }
}
