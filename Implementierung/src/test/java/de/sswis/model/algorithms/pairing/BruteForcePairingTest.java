package de.sswis.model.algorithms.pairing;

import de.sswis.model.Agent;
import de.sswis.model.CombinedStrategy;
import de.sswis.model.Group;
import de.sswis.model.Pair;
import de.sswis.model.conditions.Always;
import de.sswis.model.conditions.Condition;
import de.sswis.model.strategies.AlwaysCooperate;
import de.sswis.model.strategies.BaseStrategy;
import org.junit.BeforeClass;
import org.junit.Test;


import static org.junit.Assert.assertTrue;

public class BruteForcePairingTest {
    private static Agent[] agents;
    private static BruteForcePairing pairingAlgorithm;
    private static CombinedStrategy always;
    private static Group group;

    @BeforeClass
    public static void init() {
        pairingAlgorithm = new BruteForcePairing();
        agents = new Agent[6];
        group = new Group(1, "1");
        always = new CombinedStrategy("Always", new BaseStrategy[]{new AlwaysCooperate()},
                new Condition[]{new Always()});
    }

    @Test
    public void pairingContainsEveryAgentOnce() {
        agents = new Agent[6];
        for(int i = 0; i < agents.length; i++) {
            agents[i] = new Agent(i, 0, group, always);
        }
        Pair[] pairs = pairingAlgorithm.getPairing(agents, null);

        for(Agent agent : agents) {
            assertTrue(containsAgentOnce(pairs, agent));
        }
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
}
