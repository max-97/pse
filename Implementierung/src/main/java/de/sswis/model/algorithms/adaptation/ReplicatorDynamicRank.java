package de.sswis.model.algorithms.adaptation;

import de.sswis.model.Agent;

import java.util.HashMap;
import java.util.Random;

/**
 * Ein Algorithmus der die Strategie eines Agenten einer Simulation anpasst, in Abhaengigkeit von der Differenz
 * der Raenge zweier verglichener Agenten. Ein Agent uebernimmt die Strategie eines anderen mit einer Wahrscheinlichkeit
 * delta*beta, wobei delta die nichtnegative Differenz der Raenge ist und beta eine Konstante,
 * so dass delta*beta zwischen 0 und 1 liegt.
 * @author Michel Bodé
 */
public class ReplicatorDynamicRank implements AdaptationAlgorithm {

    public static final String NAME = "Replicator Dynamic Rank";
    public static final String DESCRIPTION = "";
    private static final String[] PARAMETER_NAMES = {};

    public ReplicatorDynamicRank() {}

    @Override
    public int adapt(Agent[] agents, HashMap<Agent, Integer> currentRanking, double adaptationProbability) {
        int adaptationCount = 0;
        Random rnd = new Random();
        double beta = 1.0 / (agents.length - 1);

        for(int i = 0; i < agents.length; i++) {
            double rndDouble = rnd.nextDouble();
            if(rndDouble <= adaptationProbability && rndDouble != 0) {
                Agent randomAgent = agents[rnd.nextInt(agents.length)];
                if(currentRanking.get(randomAgent) < currentRanking.get(agents[i])
                        && rndDouble < (Math.abs(currentRanking.get(agents[i]) - currentRanking.get(randomAgent)) * beta)
                        && !randomAgent.getStrategy().getName().equals(agents[i].getStrategy().getName())){
                    agents[i].setStrategy(randomAgent.getStrategy().clone());
                    adaptationCount++;
                }
            }
        }
        return adaptationCount;
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
