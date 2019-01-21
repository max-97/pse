package de.sswis.model.algorithms.ranking;

import de.sswis.model.Agent;

import java.util.*;

/**
 * Ein Algorithmus der die Agenten einer Simulation entsprechend ihrer bisherigen Gesamtpunktzahl bewertet.
 * Hat ein Agent eine hoehere Punktzahl als ein anderer, so ist auch sein Rang hoeher.
 * @author Michel Bodé
 */
public class Score implements  RankingAlgorithm {
    public static final String NAME = "";
    public static final String DESCRIPTION = "";
    public static final int PARAMETER_COUNT = 0;
    public static final String[] PARAMETER_NAMES = {};

    @Override
    public HashMap<Agent, Integer> getRankings(Agent[] agents) {
        HashMap<Agent, Integer> result = new HashMap<>();
        List<Agent> agentList = new ArrayList<>(agents.length);

        for(int i = 0; i < agents.length; i++) {
            agentList.add(agents[i]);
        }

        Collections.sort(agentList, (a1, a2) -> a2.getHistory().getScore() - a1.getHistory().getScore());
        Iterator<Agent> it = agentList.iterator();

        int count = 1;
        int previousScore = 0;
        boolean first = true;
        while(it.hasNext()) {
            Agent current = it.next();
            if(!first && previousScore != current.getHistory().getScore()) {
                count++;
            }
            result.put(current, count);
            previousScore = current.getHistory().getScore();
            first = false;
        }
        return result;
    }
}
