package de.sswis.model.algorithms.ranking;

import de.sswis.model.Agent;

import java.util.*;

/**
 * Ein Algorithmus der die Agenten einer Simulation entsprechend ihrer Punktzahl im aktuellen Zyklus bewertet.
 * Hat ein Agent eine hoehere Punktzahl als ein anderer, so ist auch sein Rang hoeher.
 * @author Michel Bodé
 */
public class CurrentCycleScore implements RankingAlgorithm {

    public static final String NAME = "";
    public static final String DESCRIPTION = "";
    public static final int PARAMETER_COUNT = 0;
    public static final String[] PARAMETER_NAMES = {};

    @Override
    public HashMap<Agent, Integer> getRankings(Agent[] agents) {
        HashMap<Agent, Integer> cycleScores = new HashMap<>();
        HashMap<Agent, Integer> result = new HashMap<>();
        List<Agent> agentList = new ArrayList<>(agents.length);
        int currentCycle = agents[1].getHistory().getCurrentCycle();

        for(int i = 0; i < agents.length; i++) {
            cycleScores.put(agents[i], agents[i].getScore() - agents[i].getHistory().getScore(currentCycle));
            agentList.add(agents[i]);
        }


        agentList.sort((a1, a2) -> cycleScores.get(a2) > cycleScores.get(a1) ? 1 :
                cycleScores.get(a2) < cycleScores.get(a1) ? -1 : 0);
        Iterator<Agent> it = agentList.iterator();

        int count = 1;
        int previousScore = 0;
        boolean first = true;
        while(it.hasNext()) {
            Agent current = it.next();
            if(!first && previousScore != cycleScores.get(current)) {
                count++;
            }
            result.put(current, count);
            previousScore = cycleScores.get(current);
            first = false;
        }
        return result;
    }
}
