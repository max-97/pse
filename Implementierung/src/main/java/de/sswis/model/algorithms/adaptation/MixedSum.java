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
 * durch Summenbildung und anschliessende Normierung anpasst. Die Wahrscheinlichkeiten der beiden gemischten
 * Strategien werden addiert und anschliessend normiert, so dass die Summe der neuen Wahrscheinlichkeiten
 * wieder 1 ergibt.
 * @author Michel Bodé
 */
public class MixedSum implements AdaptationAlgorithm{

    public static final String NAME = "Mixed Sum";
    public static final String DESCRIPTION = "";
    private static final String[] PARAMETER_NAMES = {};
    private Random rnd;

    public MixedSum() {}

    @Override
    public int adapt(Agent[] agents, HashMap<Agent, Integer> currentRanking, double adaptationProbability) {
        int adaptationCount = 0;
        rnd = new Random();
        for(int i = 0; i < agents.length; i++) {
            double rndDouble = rnd.nextDouble();
            if(rndDouble < adaptationProbability) {
                Agent randomAgent = agents[rnd.nextInt(agents.length)];
                if(currentRanking.get(randomAgent) < currentRanking.get(agents[i])) {
                    adaptStrategy(agents[i], randomAgent.getStrategy());
                    adaptationCount++;
                }

            }
        }
        return adaptationCount;
    }

    private void adaptStrategy(Agent agent, Strategy strategy) {
        CombinedStrategy[] oldStrategies;
        double[] oldProbabilities;

        if(agent.getStrategy() instanceof CombinedStrategy) {
            oldStrategies = new CombinedStrategy[]{(CombinedStrategy)strategy};
            oldProbabilities = new double[]{1.0};
        } else {
            oldStrategies = ((MixedStrategy)agent.getStrategy()).getCombinedStrategies();
            oldProbabilities = ((MixedStrategy)agent.getStrategy()).getProbabilities();
        }

        HashSet<CombinedStrategy> allStrategies = new HashSet<>();
        HashMap<CombinedStrategy, Double> allProbabilities = new HashMap<>();

        for(int i = 0; i < oldStrategies.length; i++) {
            allStrategies.add(oldStrategies[i]);
            allProbabilities.put(oldStrategies[i], oldProbabilities[i]);
        }

        if(strategy instanceof CombinedStrategy) {
            allStrategies.add((CombinedStrategy)strategy);
            if(allProbabilities.containsKey(strategy)) {
                allProbabilities.replace((CombinedStrategy)strategy,
                        allProbabilities.get(strategy) + 1);
            } else {
                allProbabilities.put((CombinedStrategy)strategy, 1.0);
            }
        } else {
            MixedStrategy mixedStrategy = (MixedStrategy)strategy;
            for(int i = 0; i < mixedStrategy.getCombinedStrategies().length; i++) {
                allStrategies.add(mixedStrategy.getCombinedStrategies()[i]);
                if(allProbabilities.containsKey(mixedStrategy.getCombinedStrategies()[i])) {
                    allProbabilities.replace(mixedStrategy.getCombinedStrategies()[i],
                            allProbabilities.get(mixedStrategy.getCombinedStrategies()[i]) + mixedStrategy.getProbabilities()[i]);
                } else {
                    allProbabilities.put(mixedStrategy.getCombinedStrategies()[i], mixedStrategy.getProbabilities()[i]);
                }
            }
        }

        CombinedStrategy[] newStrategies = new CombinedStrategy[allStrategies.size()];
        double[] newProbabilities = new double[allStrategies.size()];
        Iterator<CombinedStrategy> it = allStrategies.iterator();
        int count = 0;

        while(it.hasNext()) {
            CombinedStrategy currentStrategy = it.next();
            newStrategies[count] = currentStrategy;
            newProbabilities[count] = allProbabilities.get(currentStrategy);
            count++;
        }

        Strategy newStrategy;

        if(newStrategies.length == 1) {
           newStrategy = newStrategies[0];
        } else {
            normalize(newProbabilities);
            String newName = getNewName(strategy.getName());
            newStrategy = new MixedStrategy(newName, newStrategies, newProbabilities);
        }
        agent.setStrategy(newStrategy);
    }

    private void normalize(double[] probabilities) {
        double sum = 0;
        for(int i = 0; i < probabilities.length; i++) sum += probabilities[i];
        for(int i = 0; i < probabilities.length; i++) probabilities[i] *= 1/sum;
    }

    private String getNewName(String oldName) {
        if(!oldName.contains("_")) {
            return oldName + "_1";
        } else {
            int index = oldName.indexOf("_") + 1;
            int adaptCount = Integer.parseInt(oldName.substring(index)) + 1;
            return oldName.substring(0, index)  + adaptCount;
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
