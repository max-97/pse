package de.sswis.model.algorithms.ranking;

import de.sswis.model.Agent;
import org.junit.Before;

public class CustomCycleScoreTest {

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
}
