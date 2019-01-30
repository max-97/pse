package de.sswis.model.algorithms.adaptation;

import de.sswis.model.Agent;
import de.sswis.model.CombinedStrategy;
import de.sswis.model.Group;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

import java.util.HashMap;

public class RankPercentageTest {

    private RankPercentage adaptationAlgorithm;
    private HashMap<Agent, Integer> ranking;
    private Agent[] agents;

    @Before
    public void init() {
        Group group = new Group(1, "1");
        ranking = new HashMap<>();
        agents = new Agent[100];

        for(int i = 0; i < agents.length; i++) {
            agents[i] = new Agent(i + 1, 0, group, new CombinedStrategy(Integer.toString(i + 1),
                    null, null));
            ranking.put(agents[i], agents[i].getId());
        }
    }

    @Test
    public void zeroPercentage() {
        adaptationAlgorithm = new RankPercentage(0);
        for(int i = 0; i < 11; i++) {
            adaptationAlgorithm.adapt(agents, ranking, 1);
        }
        for(Agent agent : agents) {
            assertEquals(agent.getId(), Integer.parseInt(agent.getStrategy().getName()));
        }
    }

    @Test
    public void zeroAdaptationProbability() {
        adaptationAlgorithm = new RankPercentage(100);
        for(int i = 0; i < 11; i++) {
            adaptationAlgorithm.adapt(agents, ranking, 0);
        }
        for(Agent agent : agents) {
            assertEquals(agent.getId(), Integer.parseInt(agent.getStrategy().getName()));
        }
    }

    @Test
    public void adaptTest() {
        adaptationAlgorithm = new RankPercentage(50);
        for(int i = 0; i < 11; i++) {
            adaptationAlgorithm.adapt(agents, ranking, 1);
        }
        for(Agent agent : agents) {
            //only adaptation to strategies of higher ranked agents
            assertTrue(agent.getId() >= Integer.parseInt(agent.getStrategy().getName()));
            //no adaptation to strategies of agents below 50%
            if(ranking.get(agent) != Integer.parseInt(agent.getStrategy().getName())) {
                assertTrue(Integer.parseInt(agent.getStrategy().getName()) < 50);
            }
        }
    }
}
