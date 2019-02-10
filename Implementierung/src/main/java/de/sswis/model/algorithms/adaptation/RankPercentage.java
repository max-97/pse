package de.sswis.model.algorithms.adaptation;

import de.sswis.model.Agent;
import de.sswis.model.algorithms.ranking.RankingAlgorithm;

import java.util.HashMap;
import java.util.Random;

/**
 * Ein Algorithmus der die Strategie eines Agenten anpasst, wenn der Agent mit dem verglichen wird
 * zu den obersten {@code PERCENTAGE} Prozent der Rangliste gehoert. Der Agent uebernimmt die Strategie des anderen,
 * falls diese Kondition erfuellt ist. Die Strategie eines Agenten wird nicht angepasst, wenn der Rang des Agenten mit
 * dem verglichen wird tiefer ist.
 * @author Michel Bodé
 */
public class RankPercentage implements AdaptationAlgorithm {
    public static final String NAME = "Rank Percentage";
    public static final String DESCRIPTION = "";
    private static final String[] PARAMETER_NAMES = {"Percentage"};
    private int percentage;

    public RankPercentage() {
        percentage = 10;
    }

    @Override
    public int adapt(Agent[] agents, HashMap<Agent, Integer> currentRanking, double adaptationProbability) {
        int adaptationCount = 0;
        int cutOff = (int) Math.round(agents.length*(double)percentage/100);
        Random rnd = new Random();

        for(int i = 0; i < agents.length; i++) {
            double rndDouble = rnd.nextDouble();
            if(rndDouble <= adaptationProbability && rndDouble != 0) {
                Agent randomAgent = agents[rnd.nextInt(agents.length)];
                if(currentRanking.get(randomAgent) < cutOff && currentRanking.get(randomAgent) < currentRanking.get(agents[i])) {
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
        percentage = Integer.parseInt((String) parameters.get(PARAMETER_NAMES[0]));
    }

    @Override
    public String[] getParameters() {
        return PARAMETER_NAMES;
    }
}
