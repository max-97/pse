package de.sswis.model.algorithms.adaptation;

import de.sswis.model.Agent;
import de.sswis.model.CombinedStrategy;
import de.sswis.model.Group;
import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;

import static org.junit.Assert.*;

public class ReplicatorDynamicScoreTest {

    private ReplicatorDynamicScore adaptationAlgorithm;
    private HashMap<Agent, Integer> ranking;
    private Agent[] agents;


    @Before
    public void init() {
        adaptationAlgorithm = new ReplicatorDynamicScore();
    }

    @Test
    public void adaptTest() {
        ranking = new HashMap<>();
        Group group = new Group(1, "1");
        agents = new Agent[2];
        agents[0] = new Agent(1, 100, group, new CombinedStrategy("1", null, null));
        agents[1] = new Agent(2, 0, group, new CombinedStrategy("2", null, null));
        ranking.put(agents[0], 1);
        ranking.put(agents[1], 2);
        adaptationAlgorithm.adapt(agents, ranking, 1);
        //agents[1] should always adapt to agents[0]
        assertTrue(Integer.parseInt(agents[0].getStrategy().getName()) == 1);
        assertTrue(Integer.parseInt(agents[0].getStrategy().getName()) == 1);
    }

    @Test
    public void adaptTest2() {
        Group group = new Group(1, "1");
        ranking = new HashMap<>();
        agents = new Agent[100];

        for(int i = 0; i < agents.length; i++) {
            agents[i] = new Agent(i + 1, 0, group, new CombinedStrategy(Integer.toString(i + 1),
                    null, null));
            ranking.put(agents[i], agents[i].getId());
        }

        for(int i = 0; i < 11; i++) {
            adaptationAlgorithm.adapt(agents, ranking, 1);
        }
        for(Agent agent : agents) {
            //only adaptation to strategies of higher ranked agents
            assertTrue(agent.getId() >= Integer.parseInt(agent.getStrategy().getName()));
        }
    }
}
