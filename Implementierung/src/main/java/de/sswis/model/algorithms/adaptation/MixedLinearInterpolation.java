package de.sswis.model.algorithms.adaptation;

import de.sswis.model.Agent;

import java.util.HashMap;
import java.util.Random;

/**
 * Ein Algorithmus der die Wahrscheinlichkeiten der gemischten Strategien der Agenten einer Simulation
 * durch lineare Interpolation anpasst. Die Wahrscheinlichkeiten werden staerker angepasst desto groesser
 * die Differenz der Gesamtpunktzahlen der verglichenen Agenten ist.
 * @author Michel Bodé
 */
public class MixedLinearInterpolation implements AdaptationAlgorithm{

    public static final String NAME = "";
    public static final String DESCRIPTION = "";
    public static final int PARAMETER_COUNT = 0;
    public static final String[] PARAMETER_NAMES = {};
    private Random rnd;
    @Override
    public void adapt(Agent[] agents, HashMap<Agent, Integer> currentRanking, double adaptationProbability) {
        rnd = new Random();

        for(int i = 0; i < agents.length; i++) {
            double rndDouble = rnd.nextDouble();
            if(rndDouble < adaptationProbability) {
                Agent randomAgent = agents[rnd.nextInt(agents.length)];
                if(currentRanking.get(randomAgent) < currentRanking.get(agents[i])) {
                    adaptStrategy(agents[i], randomAgent);
                }
            }
        }
    }

    private void adaptStrategy(Agent agent1, Agent agent2) {
        //TODO
    }

    private void normalize(double[] probabilities) {
        double sum = 0;
        for(int i = 0; i < probabilities.length; i++) {
            sum += probabilities[i];
        }
        for(int i = 0; i < probabilities.length; i++) {
            probabilities[i] *= 1/sum;
        }

    }
}
