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

    public static final String NAME = "";
    public static final String DESCRIPTION = "";
    public static final int PARAMETER_COUNT = 0;
    public static final String[] PARAMETER_NAMES = {};
    private Random rnd;

    @Override
    public void adapt(Agent[] agents, HashMap<Agent, Integer> currentRanking, double adaptationProbability) {
        rnd = new Random();
        double beta = 1/(agents.length - 1);

        for(int i = 0; i < agents.length; i++) {
            int rndInt = rnd.nextInt(101);
            if(rndInt < adaptationProbability*100) {
                Agent randomAgent = getRandomAgent(agents);
                if(currentRanking.get(randomAgent) < currentRanking.get(agents[i]) &&
                        rndInt < (Math.abs(currentRanking.get(agents[i]) - currentRanking.get(randomAgent))*beta*100)){
                    agents[i].setStrategy(randomAgent.getStrategy());
                }
            }
        }
    }

    private Agent getRandomAgent(Agent[] agents) {
        double rndDouble = (double)rnd.nextInt(101)/100;
        return agents[(int)Math.floor(agents.length*rndDouble)];
    }
}
