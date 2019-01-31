package de.sswis.model.algorithms.ranking;

import de.sswis.model.Agent;

import java.util.*;

/**
 * Ein Algorithmus der die Agenten einer Simulation entsprechend ihrer bisherigen Gesamtpunktzahl bewertet.
 * Hat ein Agent eine hoehere Punktzahl als ein anderer, so ist auch sein Rang hoeher.
 * @author Michel Bodé
 */
public class Score implements  RankingAlgorithm {
    public static final String NAME = "Gesamtpunktzahl";
    public static final String DESCRIPTION = "";
    private static final String[] PARAMETER_NAMES = {};

    public Score() {}

    @Override
    public HashMap<Agent, Integer> getRankings(Agent[] agents) {
        HashMap<Agent, Integer> result = new HashMap<>();
        List<Agent> agentList = new ArrayList<>(agents.length);

        agentList.addAll(Arrays.asList(agents));

        agentList.sort((a1, a2) -> Integer.compare(a2.getScore(), a1.getScore()));
        Iterator<Agent> it = agentList.iterator();

        int count = 1;
        int previousScore = 0;
        boolean first = true;
        while(it.hasNext()) {
            Agent current = it.next();
            if(!first && previousScore != current.getScore()) {
                count++;
            }
            result.put(current, count);
            previousScore = current.getScore();
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

    }

    @Override
    public String[] getParameters() {
        return PARAMETER_NAMES;
    }
}
