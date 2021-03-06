package de.sswis.model.algorithms.ranking;

import de.sswis.model.Agent;
import de.sswis.model.algorithms.ranking.Score;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertEquals;

import java.util.HashMap;

public class ScoreTest {

    private Agent[] agents;
    private Score rankingAlgorithm;

    @Before
    public void init() {
        agents = new Agent[4];
        rankingAlgorithm = new Score();

        for(int i = 0; i < agents.length; i++) {
            agents[i] = new Agent(i + 1, i*10, null, null);
        }
    }

    @Test
    public void getRankingsTest() {
        agents[0].setScore(10 + agents[0].getScore());
        agents[1].setScore(15 + agents[1].getScore());
        agents[2].setScore(5 + agents[2].getScore());
        agents[3].setScore(7 + agents[3].getScore());
        HashMap<Agent, Integer> ranking = rankingAlgorithm.getRankings(agents);
        assertEquals(3, (int) ranking.get(agents[0]));
        assertEquals(2, (int) ranking.get(agents[1]));
        assertEquals(2, (int) ranking.get(agents[2]));
        assertEquals(1, (int) ranking.get(agents[3]));
    }

    @Test
    public void ignoreInitialScoreTest() {
        rankingAlgorithm.setIgnoreInitialScore(true);
        agents[0].setScore(10 + agents[0].getScore());
        agents[1].setScore(5 + agents[1].getScore());
        agents[2].setScore(5 + agents[2].getScore());
        agents[3].setScore(7 + agents[3].getScore());
        HashMap<Agent, Integer> ranking = rankingAlgorithm.getRankings(agents);
        assertEquals(1, (int) ranking.get(agents[0]));
        assertEquals(3, (int) ranking.get(agents[1]));
        assertEquals(3, (int) ranking.get(agents[2]));
        assertEquals(2, (int) ranking.get(agents[3]));
    }
}
