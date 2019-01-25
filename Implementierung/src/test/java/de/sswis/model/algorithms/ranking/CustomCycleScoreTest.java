package de.sswis.model.algorithms.ranking;

import de.sswis.model.Agent;
import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;

import static org.junit.Assert.assertEquals;

public class CustomCycleScoreTest {

    private static Agent[] agents;
    private static CustomCycleScore rankingAlgorithm;

    @Before
    public void init() {
        agents = new Agent[4];
    }

    @Test
    public void getRankingsTest1() {

        for(int i = 0; i < agents.length; i++) {
            agents[i] = new Agent(i + 1, i*100, null, null);
        }

        rankingAlgorithm = new CustomCycleScore(2);

        for(int i = 0 ; i < 3; i++) {
            agents[0].setScore(agents[0].getScore() + 10);
            agents[1].setScore(agents[1].getScore() + 5);
            agents[2].setScore(agents[2].getScore() + 5);
            agents[3].setScore(agents[3].getScore() + 1);

            for(int j = 0; j < agents.length; j++) {
                agents[j].getHistory().increaseCycleCount();
                agents[j].getHistory().setScore(agents[j].getScore());
            }
        }

        HashMap<Agent, Integer> ranking = rankingAlgorithm.getRankings(agents);
        assertEquals((int) ranking.get(agents[0]), 1);
        assertEquals((int) ranking.get(agents[1]), 2);
        assertEquals((int) ranking.get(agents[2]), 2);
        assertEquals((int) ranking.get(agents[3]), 3);
    }

    @Test
    public void getRankingsTest2() {
        for(int i = 0; i < agents.length; i++) {
            agents[i] = new Agent(i + 1, 0, null, null);
        }

        agents[0].setScore(10);
        agents[1].setScore(5);
        agents[2].setScore(5);
        agents[3].setScore(7);

        HashMap<Agent, Integer> ranking = rankingAlgorithm.getRankings(agents);
        assertEquals((int) ranking.get(agents[0]), 1);
        assertEquals((int) ranking.get(agents[1]), 3);
        assertEquals((int) ranking.get(agents[2]), 3);
        assertEquals((int) ranking.get(agents[3]), 2);
    }
}
