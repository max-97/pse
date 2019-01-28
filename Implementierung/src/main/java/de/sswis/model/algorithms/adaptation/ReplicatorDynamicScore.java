package de.sswis.model.algorithms.adaptation;

import de.sswis.model.Agent;

import java.util.HashMap;
import java.util.Random;

/**
 * Ein Algorithmus der die Strategie eines Agenten einer Simulation anpasst, in Abhaengigkeit von der Differenz
 * der Gesamtpunktzahlen zweier verglichener Agenten. Ein Agent uebernimmt die Strategie eines anderen mit einer
 * Wahrscheinlichkeit delta*beta, wobei delta die nichtnegative Differenz der Gesamtpunktzahlen ist und beta eine
 * Konstante, so dass delta*beta zwischen 0 und 1 liegt.
 * @author Michel Bodé
 */
public class ReplicatorDynamicScore implements AdaptationAlgorithm {

    public static final String NAME = "";
    public static final String DESCRIPTION = "";
    public static final int PARAMETER_COUNT = 0;
    public static final String[] PARAMETER_NAMES = {};

    @Override
    public void adapt(Agent[] agents, HashMap<Agent, Integer> currentRanking, double adaptationProbability) {
        Random rnd = new Random();
        double beta = 1.0 / getHighestScoreDifference(agents);

        for (int i = 0; i < agents.length; i++) {
            double rndDouble = rnd.nextDouble();
            if (rndDouble < adaptationProbability) {
                Agent randomAgent = agents[rnd.nextInt(agents.length)];
                int delta = Math.min(randomAgent.getScore() - agents[i].getScore(), 0);
                if (currentRanking.get(randomAgent) < currentRanking.get(agents[i]) && rndDouble < delta * beta) {
                    agents[i].setStrategy(randomAgent.getStrategy());
                }
            }
        }
    }

    private int getHighestScoreDifference(Agent[] agents) {
        int max = agents[0].getScore();
        int min = max;

        for (int i = 0; i < agents.length; i++) {
            int currentScore = agents[i].getScore();
            if (currentScore > max) {
                max = currentScore;
            }
            if (currentScore < min) {
                min = currentScore;
            }
        }
        return max - min;
    }

    @Override
    public String getName() {
        return null;
    }
}
