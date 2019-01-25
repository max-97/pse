package de.sswis.model.algorithms.ranking;

import de.sswis.model.Agent;
import de.sswis.model.algorithms.ranking.Score;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertEquals;

import java.util.HashMap;

public class ScoreTest {

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
    public void getRankingsTest() {
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
