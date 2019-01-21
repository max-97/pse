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

    public static final String NAME = "";
    public static final String DESCRIPTION = "";
    public static final int PARAMETER_COUNT = 1;
    public static final String[] PARAMETER_NAMES = {"Window size"};
    private final int WINDOW_SIZE;
    private int cycleRoundCount;
    private int currentRounds;

    /**
     * Konstruktor
     * @param cycleRoundCount Rundenanzahl eines Zyklus
     * @param WINDOW_SIZE Anzahl der zu betrachtenden Zyklen
     */
    public CustomCycleScore(int cycleRoundCount, int WINDOW_SIZE) {
        this.cycleRoundCount = cycleRoundCount;
        this.WINDOW_SIZE = WINDOW_SIZE;
    }

    @Override
    public HashMap<Agent, Integer> getRankings(Agent[] agents) {
        HashMap<Agent, Integer> cyclesScores = new HashMap<>();
        HashMap<Agent, Integer> result = new HashMap<>();
        List<Agent> agentList = new ArrayList<>(agents.length);
        currentRounds = agents[1].getHistory().getCurrentRound();
        int start = Math.max(1, currentRounds - cycleRoundCount*WINDOW_SIZE);

        for(int i = 0; i < agents.length; i++) {
            cyclesScores.put(agents[i], agents[i].getHistory().getScore() - agents[i].getHistory().getScore(start));
            agentList.add(agents[i]);
        }


        Collections.sort(agentList, (a1, a2) -> cyclesScores.get(a2) - cyclesScores.get(a1));
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
}
