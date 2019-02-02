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
    public static final String NAME = "Durschnittsrang";
    public static final String DESCRIPTION = "";
    private static final String[] PARAMETER_NAMES = {"Window size"};
    private int currentCycle;
    private int windowSize;
    private boolean ignoreInitialScore;

    public AverageRank() {
        windowSize = 5;
        ignoreInitialScore = false;
    }

    @Override
    public HashMap<Agent, Integer> getRankings(Agent[] agents) {
        HashMap<Agent, Integer> averageRanks = new HashMap<>();
        HashMap<Agent, Integer> result = new HashMap<>();
        List<Agent> agentList = new ArrayList<>(agents.length);
        currentCycle = agents[1].getHistory().getCurrentCycle();
        agentList.addAll(Arrays.asList(agents));
        HashMap<Agent, Integer[]> cycleRankings = getCycleRankings(agentList);

        for(int i = 0; i < agents.length; i++) {
            int rankSum = 0;
            Integer[] agentCycleRanks = cycleRankings.get(agents[i]);
            for(int j = 0; j < agentCycleRanks.length; j++) {
                rankSum += agentCycleRanks[j];
            }
            int avgRank = Math.round((float)rankSum/currentCycle);
            averageRanks.put(agents[i], avgRank);
        }

        if(ignoreInitialScore) {
            agentList.sort((a1, a2) -> averageRanks.get(a2).intValue() != averageRanks.get(a1).intValue() ?
                    averageRanks.get(a1) > averageRanks.get(a2) ? 1 : -1 :
                    Integer.compare(a2.getScore() - a2.getInitialScore(),
                            a1.getScore() - a1.getInitialScore()));
        } else {
            agentList.sort((a1, a2) -> averageRanks.get(a2).intValue() != averageRanks.get(a1).intValue() ?
                    averageRanks.get(a1) > averageRanks.get(a2) ? 1 : -1 :
                    Integer.compare(a2.getScore(), a1.getScore()));
        }

        Iterator<Agent> it = agentList.iterator();

        int count = 1;
        int previousScore = 0;
        boolean first = true;
        while(it.hasNext()) {
            Agent current = it.next();
            if(ignoreInitialScore) {
                if(!first && previousScore != current.getScore() - current.getInitialScore()) {
                    count++;
                }
                previousScore = current.getScore() - current.getInitialScore();
            } else {
                if (!first && previousScore != current.getScore()) {
                    count++;
                }
                previousScore = current.getScore();
            }
            result.put(current, count);
            first = false;
        }
        return result;
    }

    private HashMap<Agent, Integer[]> getCycleRankings(List<Agent> agentList) {
        HashMap<Agent, Integer[]> result = new HashMap<>();
        HashMap<Agent, Integer> cyclesScores = new HashMap<>();

        for (Agent agent : agentList) {
            result.put(agent, new Integer[currentCycle]);
        }

        for(int i = 1; i <= currentCycle; i++) {
            for (Agent agent : agentList) {
                if (i == currentCycle) {
                    cyclesScores.put(agent, agent.getScore() -
                            agent.getHistory().getScore(Math.max(i - windowSize, 1)));
                } else {
                    cyclesScores.put(agent, agent.getHistory().getScore(i) -
                            agent.getHistory().getScore(Math.max(i - windowSize, 1)));
                }
            }

            agentList.sort((a1, a2) -> cyclesScores.get(a2) > cyclesScores.get(a1) ? 1 :
                    cyclesScores.get(a2) < cyclesScores.get(a1) ? -1 : 0);
            Iterator<Agent> agentIterator = agentList.iterator();
            int rankCount = 1;
            int previousScore = 0;
            boolean first = true;

            while(agentIterator.hasNext()) {
                Agent current = agentIterator.next();
                if(!first && previousScore != cyclesScores.get(current)) {
                    rankCount++;
                }
                result.get(current)[i - 1] = rankCount;
                previousScore = cyclesScores.get(current);
                first = false;
            }
            cyclesScores.clear();
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
