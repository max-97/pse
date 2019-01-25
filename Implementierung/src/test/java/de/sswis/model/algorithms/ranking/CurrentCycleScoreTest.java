package de.sswis.model.algorithms.ranking;

import de.sswis.model.Agent;
import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;

import static org.junit.Assert.assertEquals;

public class CurrentCycleScoreTest {

    private static Agent[] agents;
    private static Score rankingAlgorithm;

    @Before
    public void init() {
        agents = new Agent[4];
        rankingAlgorithm = new Score();

        for(int i = 0; i < agents.length; i++) {
            agents[i] = new Agent(i + 1, 0, null, null);
        }
    }

    @Test
    public void getRankingsTest1() {
        agents[0].setScore(10);
        agents[1].setScore(5);
        agents[2].setScore(5);
        agents[3].setScore(7);

        for(int i = 0; i < agents.length; i++) {
            agents[i].getHistory().increaseCycleCount();
            agents[i].getHistory().setScore(agents[i].getScore());
            agents[i].setScore(10 - i);
        }

        HashMap<Agent, Integer> ranking = rankingAlgorithm.getRankings(agents);
        assertEquals((int) ranking.get(agents[0]), 1);
        assertEquals((int) ranking.get(agents[1]), 2);
        assertEquals((int) ranking.get(agents[2]), 3);
        assertEquals((int) ranking.get(agents[3]), 4);
    }

}
