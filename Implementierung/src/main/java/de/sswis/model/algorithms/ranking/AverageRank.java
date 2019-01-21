package de.sswis.model.algorithms.ranking;

import de.sswis.model.Agent;

import java.util.*;

/**
 * Ein Algorithmus der die Agenten einer Simulation entsprechend ihres Durchschnittsranges ueber alle bisherigen
 * Zyklen bewertet. Fuer jeden bisherigen Zyklus wird die Gesamtpunktzahl der vorherigen {@code WINDOW_SIZE} Zyklen
 * bestimmt und die Agenten erhalten entsprechend dieser Punktzahl einen Rang. Der Durchschnitt dieser Raenge
 * ueber alle bisherigen Zyklen ist der finale Rang.
 * @author Michel Bodé
 */
public class AverageRank implements RankingAlgorithm {
    public static final String NAME = "";
    public static final String DESCRIPTION = "";
    public static final int PARAMETER_COUNT = 1;
    public static final String[] PARAMETER_NAMES = {"Window size"};
    private int cycleRoundCount;
    private int currentRounds;
    private int cycleCount;
    private final int WINDOW_SIZE;

    /**
     * Konstruktor
     * @param WINDOW_SIZE Anzahl der zu betrachtenden Zyklen
     * @param cycleRoundCount Rundenanzahl eines Zyklus
     */
    public AverageRank(int cycleRoundCount, int WINDOW_SIZE) {
        this.cycleRoundCount = cycleRoundCount;
        this.WINDOW_SIZE = WINDOW_SIZE;
    }

    @Override
    public HashMap<Agent, Integer> getRankings(Agent[] agents) {
        HashMap<Agent, Integer> averageRanks = new HashMap<>();
        HashMap<Agent, Integer> result = new HashMap<>();
        List<Agent> agentList = new ArrayList<>(agents.length);
        int[][] cycleRankings = new int[cycleCount][agents.length];
        currentRounds = agents[1].getHistory().getCurrentRound();
        cycleCount = currentRounds/cycleRoundCount + 1;

        for(int i = 0; i < agents.length; i++) {
            agentList.add(agents[i]);
        }

        for(int i = 1; i <= cycleCount; i++) {
            cycleRankings[i] = getCycleRankings(agentList, Math.min(i*cycleRoundCount, currentRounds));
        }

        for(int i = 0; i < agents.length; i++) {
            int rankSum = 0;
            for(int j = 0; j < cycleCount; j++) {
                rankSum += cycleRankings[j][i];
            }
            int avgRank = Math.round((float)rankSum/cycleCount);
            averageRanks.put(agents[i], avgRank);
        }

        Collections.sort(agentList, (a1, a2) -> averageRanks.get(a2) != averageRanks.get(a1) ?
                averageRanks.get(a2) - averageRanks.get(a1) :
                a2.getHistory().getScore() - a1.getHistory().getScore());
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

    private int[] getCycleRankings(List<Agent> agentList, int end) {
        HashMap<Agent, Integer> cyclesScores = new HashMap<>();
        int[] result = new int[agentList.size()];

        for(int i = 0; i < agentList.size(); i++) {
            cyclesScores.put(agentList.get(i), agentList.get(i).getHistory().getScore(end) -
                    agentList.get(i).getHistory().getScore(1));
        }

        Collections.sort(agentList, (a1, a2) -> cyclesScores.get(a2) - cyclesScores.get(a1));
        Iterator<Agent> it = agentList.iterator();

        int rankCount = 1;
        int agentCount = 0;
        int previousScore = 0;
        boolean first = true;
        while(it.hasNext()) {
            Agent current = it.next();
            if(!first && previousScore != cyclesScores.get(current)) {
                rankCount++;
            }
            result[agentCount++] = rankCount;
            previousScore = cyclesScores.get(current);
            first = false;
        }
        return result;
    }
}
