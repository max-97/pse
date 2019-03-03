package de.sswis.model.algorithms.ranking;

import de.sswis.model.Agent;
import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;

import static org.junit.Assert.assertEquals;

public class AverageRankTest {

    private static Agent[] agents;
    private static AverageRank rankingAlgorithm;

    @Before
    public void init() {
        agents = new Agent[4];
        rankingAlgorithm = new AverageRank();
        HashMap<String, Object> parameters = new HashMap<>();
        parameters.put(rankingAlgorithm.getParameters()[0], "2");
        rankingAlgorithm.setParameters(parameters);

        for(int i = 0; i < agents.length; i++) {
            agents[i] = new Agent(i + 1, 0, null, null);
            agents[i].getHistory().setScore(agents[i].getScore());
        }
    }


    @Test
    public void getRankingsTest1() {
        agents[0].setScore(10);
        agents[1].setScore(5);
        agents[2].setScore(5);
        agents[3].setScore(7);

        HashMap<Agent, Integer> ranking = rankingAlgorithm.getRankings(agents);

        assertEquals(1, (int) ranking.get(agents[0]));
        assertEquals(3, (int) ranking.get(agents[1]));
        assertEquals(3, (int) ranking.get(agents[2]));
        assertEquals(2, (int) ranking.get(agents[3]));
    }

    @Test
    public void getRankingsTest2() {
        int[][] agentScores = {{10, 5, 5, 7}, {0, 25, 20, 13}, {100, 40, 40, 10}};

        for(int j = 0; j < agentScores.length; j++) {
            for(int i = 0; i < agents.length; i++) {
                if(j != 0) agents[i].getHistory().increaseCycleCount();
                agents[i].setScore(agentScores[j][i]);
                agents[i].getHistory().setScore(agents[i].getScore());
            }
        }

        HashMap<Agent, Integer> ranking = rankingAlgorithm.getRankings(agents);
        assertEquals(2, (int) ranking.get(agents[0]));
        assertEquals(1, (int) ranking.get(agents[1]));
        assertEquals(3, (int) ranking.get(agents[2]));
        assertEquals(4, (int) ranking.get(agents[3]));
    }

    @Test
    public void ignoreInitialScoreTest() {
        int[][] agentScores = {{10, 5, 5, 7}, {11, 6, 6, 8}, {12, 7, 7, 9}};
        rankingAlgorithm.setIgnoreInitialScore(true);

        for(int j = 0; j < agentScores.length; j++) {
            for(int i = 0; i < agents.length; i++) {
                if(j != 0) {
                    agents[i].getHistory().increaseCycleCount();
                } else {
                    agents[i].setInitialScore(agentScores[j][i]);
                }
                agents[i].setScore(agentScores[j][i]);
                agents[i].getHistory().setScore(agents[i].getScore());
            }
        }

        HashMap<Agent, Integer> ranking = rankingAlgorithm.getRankings(agents);
        assertEquals(1, (int) ranking.get(agents[0]));
        assertEquals(1, (int) ranking.get(agents[1]));
        assertEquals(1, (int) ranking.get(agents[2]));
        assertEquals(1, (int) ranking.get(agents[3]));
    }
}
