package de.sswis.model.algorithms.pairing;

import de.sswis.model.Agent;
import de.sswis.model.CombinedStrategy;
import de.sswis.model.Group;
import de.sswis.model.Pair;
import de.sswis.model.conditions.Always;
import de.sswis.model.conditions.Condition;
import de.sswis.model.strategies.AlwaysCooperate;
import de.sswis.model.strategies.BaseStrategy;
import de.sswis.model.strategies.NeverCooperate;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.HashMap;

import static org.junit.Assert.assertTrue;

public class BruteForcePairingHeuristicTest {
    private static Agent[] agents;
    private static BruteForcePairingHeuristic pairingAlgorithm;
    private static CombinedStrategy always;
    private static CombinedStrategy never;
    private static Group group;


    @BeforeClass
    public static void init() {
        pairingAlgorithm = new BruteForcePairingHeuristic();
        agents = new Agent[6];
        group = new Group(1, "1");
        always = new CombinedStrategy("Always", new BaseStrategy[]{new AlwaysCooperate()},
                new Condition[]{new Always()});
        never = new CombinedStrategy("Never", new BaseStrategy[]{new NeverCooperate()},
                new Condition[]{new Always()});
    }

    @Test
    public void pairingContainsEveryAgentOnce() {
        agents = new Agent[6];
        for(int i = 0; i < agents.length; i++) {
            agents[i] = new Agent(i, 0, group, always);
        }
        Pair[] pairs = pairingAlgorithm.getPairing(agents, null);
        HashMap<String, Object> parameters = new HashMap<>();
        parameters.put("Threshold", "0.1");
        pairingAlgorithm.setParameters(parameters);

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
