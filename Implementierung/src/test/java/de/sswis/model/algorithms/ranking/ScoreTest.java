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
        agents = new Agent[10];
        rankingAlgorithm = new Score();

        for(int i = 0; i < 10; i++) {
            agents[i] = new Agent(i + 1, 0, null, null);
        }

        for(int i = 0; i < agents.length; i++) {
            agents[i].getHistory().increaseRoundCount();
            agents[i].getHistory().setScore(10 - i);
        }
    }

    @Test
    public void rankingShouldBeCorrect() {
        HashMap<Agent, Integer> ranking = rankingAlgorithm.getRankings(agents);
        for(int i = 0; i < 10; i++) {
            assertEquals((int) ranking.get(agents[i]), agents[i].getId());
        }
    }
}
