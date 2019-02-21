package de.sswis.model.algorithms.adaptation;

import de.sswis.model.Agent;
import de.sswis.model.CombinedStrategy;
import de.sswis.model.MixedStrategy;
import de.sswis.model.Strategy;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Random;

/**
 * Ein Algorithmus der die Wahrscheinlichkeiten der gemischten Strategien der Agenten einer Simulation
 * durch lineare Interpolation anpasst. Die Wahrscheinlichkeiten werden staerker angepasst desto groesser
 * die Differenz der Gesamtpunktzahlen der verglichenen Agenten ist.
 * @author Michel Bodé
 */
public class MixedLinearInterpolation implements AdaptationAlgorithm{

    public static final String NAME = "Mixed Linear Interpolation";
    public static final String DESCRIPTION = "";
    private static final String[] PARAMETER_NAMES = {};
    private int highestScoreDifference;

    public MixedLinearInterpolation() {

    }

    @Override
    public int adapt(Agent[] agents, HashMap<Agent, Integer> currentRanking, double adaptationProbability) {
        int adaptationCount = 0;
        Random rnd = new Random();
        highestScoreDifference = getHighestScoreDifference(agents);

        for(int i = 0; i < agents.length; i++) {
            double rndDouble = rnd.nextDouble();
            if(rndDouble <= adaptationProbability && rndDouble != 0) {
                Agent randomAgent = agents[rnd.nextInt(agents.length)];
                if(currentRanking.get(randomAgent) < currentRanking.get(agents[i])
                        && !randomAgent.getStrategy().getName().equals(agents[i].getStrategy().getName())) {
                    adaptStrategy(agents[i], randomAgent);
                    adaptationCount++;
                }
            }
        }
        return adaptationCount;
    }

    private void adaptStrategy(Agent agent1, Agent agent2) {
        double adapt = (double)Math.max(agent2.getScore() - agent1.getScore(), 0)/Math.max(highestScoreDifference, 1);
        Strategy strategy = agent2.getStrategy();

        CombinedStrategy[] oldStrategies;
        double[] oldProbabilities;

        if(agent1.getStrategy() instanceof CombinedStrategy) {
            oldStrategies = new CombinedStrategy[]{(CombinedStrategy)agent1.getStrategy()};
            oldProbabilities = new double[]{1.0};
        } else {
            oldStrategies = ((MixedStrategy)agent1.getStrategy()).getCombinedStrategies();
            oldProbabilities = ((MixedStrategy)agent1.getStrategy()).getProbabilities();
        }

        HashSet<CombinedStrategy> allStrategies = new HashSet<>();
        HashMap<CombinedStrategy, Double> allProbabilities = new HashMap<>();

        for(int i = 0; i < oldStrategies.length; i++) {
            allStrategies.add(oldStrategies[i]);
            allProbabilities.put(oldStrategies[i], oldProbabilities[i] * (1 - adapt));
        }

        if(strategy instanceof CombinedStrategy) {
            allStrategies.add((CombinedStrategy)strategy);
            if(allProbabilities.containsKey(strategy)) {
                allProbabilities.replace((CombinedStrategy)strategy,
                        allProbabilities.get(strategy) + adapt);
            } else {
                allProbabilities.put((CombinedStrategy)strategy, adapt);
            }
        } else {
            MixedStrategy mixedStrategy = (MixedStrategy)strategy;
            for(int i = 0; i < mixedStrategy.getCombinedStrategies().length; i++) {
                allStrategies.add(mixedStrategy.getCombinedStrategies()[i]);
                if(allProbabilities.containsKey(mixedStrategy.getCombinedStrategies()[i])) {
                    allProbabilities.replace(mixedStrategy.getCombinedStrategies()[i],
                            allProbabilities.get(mixedStrategy.getCombinedStrategies()[i])
                                    + mixedStrategy.getProbabilities()[i] * adapt);
                } else {
                    allProbabilities.put(mixedStrategy.getCombinedStrategies()[i],
                            mixedStrategy.getProbabilities()[i] * adapt);
                }
            }
        }

        int strategyCount = 0;
        for(CombinedStrategy combinedStrategy : allStrategies) {
            if(allProbabilities.get(combinedStrategy) != 0) {
                strategyCount++;
            }
        }

        CombinedStrategy[] newStrategies = new CombinedStrategy[strategyCount];
        double[] newProbabilities = new double[strategyCount];
        Iterator<CombinedStrategy> it = allStrategies.iterator();
        int count = 0;

        while(it.hasNext()) {
            CombinedStrategy currentStrategy = it.next();
            if(allProbabilities.get(currentStrategy) != 0) {
                newStrategies[count] = currentStrategy;
                newProbabilities[count] = allProbabilities.get(currentStrategy);
                count++;
            }
        }

        Strategy newStrategy;

        if(newStrategies.length == 1) {
            newStrategy = newStrategies[0];
        } else {
            newStrategy = new MixedStrategy(agent1.getStrategy().getName(), newStrategies, newProbabilities);
            if(agent1.getStrategy() instanceof  MixedStrategy) {
                ((MixedStrategy)newStrategy).setAdaptationCount(((MixedStrategy)agent1.getStrategy())
                        .getAdaptationCount() + 1);
            }
        }
        agent1.setStrategy(newStrategy);
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
