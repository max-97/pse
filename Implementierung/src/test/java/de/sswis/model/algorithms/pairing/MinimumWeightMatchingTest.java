package de.sswis.model.algorithms.pairing;

import de.sswis.model.*;
import de.sswis.model.conditions.Always;
import de.sswis.model.conditions.Condition;
import de.sswis.model.conditions.OwnGroup;
import de.sswis.model.strategies.AlwaysCooperate;
import de.sswis.model.strategies.BaseStrategy;
import de.sswis.model.strategies.NeverCooperate;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class MinimumWeightMatchingTest {
    private static Agent[] agents;
    private static MinimumWeightMatching pairingAlgorithm;
    private static CombinedStrategy always;
    private static CombinedStrategy sameGroup;
    private static Group group1;
    private static Group group2;

    @BeforeClass
    public static void init() {
        pairingAlgorithm = new MinimumWeightMatching();
        group1 = new Group(1, "1");
        group2 = new Group(2, "2");
        always = new CombinedStrategy("Always", new BaseStrategy[]{new AlwaysCooperate()},
                new Condition[]{new Always()});
        sameGroup = new CombinedStrategy("Same Group", new BaseStrategy[]{new AlwaysCooperate()},
                new Condition[]{new OwnGroup()});
    }

    @Test
    public void pairingContainsEveryAgentOnce() {
        agents = new Agent[6];
        for(int i = 0; i < agents.length; i++) {
            agents[i] = new Agent(i, 0, group1, always);
        }
        Pair[] pairs = pairingAlgorithm.getPairing(agents, null);

        for(Agent agent : agents) {
            assertTrue(containsAgentOnce(pairs, agent));
        }
    }

    @Test
    public void pairingIsOptimal() {
        agents = new Agent[4];
        agents[0] = new Agent(0, 0, group1, always);
        agents[1] = new Agent(1, 0, group1, sameGroup);
        agents[2] = new Agent(2, 0, group2, always);
        agents[3] = new Agent(3, 0, group2, sameGroup);
        Pair[] pairs = pairingAlgorithm.getPairing(agents, null);
        assertTrue(containsPair(pairs, agents[0], agents[1]));
        assertTrue(containsPair(pairs, agents[2], agents[3]));
    }

    @Test
    public void pairingIsOptimalMixedStrategies() {
        agents = new Agent[4];
        agents[0] = new Agent(0, 0, group1, new MixedStrategy("50/50",
                new CombinedStrategy[]{always, sameGroup}, new double[]{0.5, 0.5}));
        agents[1] = new Agent(1, 0, group1, new MixedStrategy("10/90",
                new CombinedStrategy[]{always, sameGroup}, new double[]{0.1, 0.9}));
        agents[2] = new Agent(2, 0, group2, always);
        agents[3] = new Agent(3, 0, group2, new MixedStrategy("50/50",
                new CombinedStrategy[]{always, sameGroup}, new double[]{0.5, 0.5}));
        Pair[] pairs = pairingAlgorithm.getPairing(agents, null);
        assertTrue(containsPair(pairs, agents[0], agents[1]));
        assertTrue(containsPair(pairs, agents[2], agents[3]));
    }

    private boolean containsAgentOnce(Pair[] pairs, Agent agent) {
        int count = 0;
        for(Pair pair : pairs) {
            if(pair.getAgent(1).getId() == agent.getId()) {
                count++;
            }
            if(pair.getAgent(2).getId() == agent.getId()) {
                count++;
            }
        }
        return count == 1;
    }

    private boolean containsPair(Pair[] pairs, Agent agent1, Agent agent2) {
        for(Pair pair : pairs) {
            if((pair.getAgent(1).equals(agent1)  && pair.getAgent(2).equals(agent2))
                    || (pair.getAgent(1).equals(agent2) && pair.getAgent(2).equals(agent1))) {
                return true;
            }
        }
        return false;
    }
}
