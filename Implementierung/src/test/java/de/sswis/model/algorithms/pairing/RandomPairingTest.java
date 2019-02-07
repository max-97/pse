package de.sswis.model.algorithms.pairing;

import de.sswis.model.Agent;
import de.sswis.model.Pair;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class RandomPairingTest {

    private static Agent[] agents;
    private static RandomPairing pairingAlgorithm;

    @Before
    public void init() {
        pairingAlgorithm = new RandomPairing();
        agents = new Agent[6];
    }

    @Test
    public void pairingContainsEveryAgentOnce() {
        for(int i = 0; i < agents.length; i++) {
            agents[i] = new Agent(i, 0, null, null);
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


