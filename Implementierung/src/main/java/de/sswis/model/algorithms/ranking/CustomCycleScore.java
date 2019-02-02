package de.sswis.model.algorithms.ranking;

import de.sswis.model.Agent;


import java.util.*;

/**
 * Ein Algorithmus der die Agenten einer Simulation entsprechend ihrer Punktzahl in den bisherigen
 * {@code WINDOW_SIZE} Zyklen bewertet.
 * Hat ein Agent eine hoehere Punktzahl als ein anderer, so ist auch sein Rang hoeher.
 * @author Michel Bodé
 */
public class CustomCycleScore implements RankingAlgorithm {

    public static final String NAME = "Letzte w Zyklen";
    public static final String DESCRIPTION = "";
    private static final String[] PARAMETER_NAMES = {"Window size"};
    private int windowSize;
    private boolean ignoreInitialScore;

    public CustomCycleScore() {
        windowSize = 5;
        ignoreInitialScore = false;
    }


    @Override
    public HashMap<Agent, Integer> getRankings(Agent[] agents) {
        HashMap<Agent, Integer> cyclesScores = new HashMap<>();
        HashMap<Agent, Integer> result = new HashMap<>();
        List<Agent> agentList = new ArrayList<>(agents.length);
        int startCycle = Math.max(agents[1].getHistory().getCurrentCycle() - windowSize, 1);

        for (Agent agent : agents) {
            cyclesScores.put(agent, agent.getScore() - agent.getHistory().getScore(startCycle));
            agentList.add(agent);
        }


        agentList.sort((a1, a2) -> cyclesScores.get(a2) > cyclesScores.get(a1) ? 1 :
                cyclesScores.get(a2) < cyclesScores.get(a1) ? -1 : 0);
        Iterator<Agent> it = agentList.iterator();

        int count = 1;
        int previousScore = 0;
        boolean first = true;
        while(it.hasNext()) {
            Agent current = it.next();
            if(!first && previousScore != cyclesScores.get(current)) {
                count++;
            }
            result.put(current, count);
            previousScore = cyclesScores.get(current);
            first = false;
        }
        return result;
    }

    @Override
    public String getName() {
        return NAME;
    }

    @Override
    public void setParameters(HashMap<String, Object> parameters) {
        windowSize = (int)parameters.get("Window size");
    }

    @Override
    public String[] getParameters() {
        return PARAMETER_NAMES;
    }

    @Override
    public void setIgnoreInitialScore(boolean ignore) {
        this.ignoreInitialScore = ignore;
    }
}
