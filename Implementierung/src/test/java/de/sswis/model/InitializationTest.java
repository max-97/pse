package de.sswis.model;

import de.swwis.util.AgentDistribution;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import de.sswis.model.strategies.AlwaysCooperate;
import de.sswis.model.strategies.NeverCooperate;

public class InitializationTest {
    private static AgentDistribution[] agentDistributions;
    private static int[] ids;
    private Initialization init;
    private Group[] groups;

    @Before
    public void init() {
        init = new Initialization("init", 10);
        ids = int[3]{1, 5, 9};
        agentDistributions = new AgentDistribution[3];
        agentDistributions[0] = new AgentDistribution(ids);
        agentDistributions[1] = new AgentDistribution(45);
        agentDistributions[2] = new AgentDistribution(55);
        agentDistributions[3] = new AgentDistribution(100);
        groups = new Group[2];
        groups[0] = new Group(0, "Group1");
        groups[1] = new Group(1, "Group2");
    }

    @Test
    public void setGroupDistributionTest() {
        Agent[] agents = init.calculateInitialAgentState();
        init.addGroup(groups[0]);
        init.addGroup(groups[1]);
        init.setGroupDistribution(agentDistributions[0], groups[0]);
        init.setGroupDistribution(agentDistributions[1], groups[0]);
        init.setGroupDistribution(agentDistributions[2], groups[1]);
        assertTrue(groups[0].getMembers().size() == 5);
        assertTrue(groups[1].getMembers().size() == 5);
        assertTrue(groups[0].getMembers().contains(agents[1]));
        assertTrue(groups[0].getMembers().contains(agents[5]));
        assertTrue(groups[0].getMembers().contains(agents[9]));
    }

    @Test
    public void setStrategyDistributionTest() {
        Strategy always = new AlwaysCooperate();
        Strategy never = new NeverCooperate();
        Agent[] agents = init.calculateInitialAgentState();
        init.addGroup(groups[0]);
        init.setGroupDistribution(agentDistributions[3], groups[0]);
        init.setStrategyDistribution(agentDistributions[0], always, groups[0]);
        init.setStrategyDistribution(agentDistributions[1], always, groups[0]);
        init.setStrategyDistribution(agentDistributions[2], never, groups[0]);
        int alwaysNumber = 0;
        int neverNumber = 0;
        for (int i = 0; i < groups[0].getMembers().size(); i++) {
            if (groups[0].getMembers().get(i),getStrategy() == always) {
                alwaysNumber++;
            }
            if (groups[0].getMembers().get(i),getStrategy() == never) {
                neverNumber++;
            }
        }
        assertTrue(alwaysNumber == 5);
        assertTrue(neverNumber == 5);
        assertEquals(always, agents[1].getStrategy());
        assertEquals(always, agents[5].getStrategy());
        assertEquals(always, agents[9].getStrategy());
    }

    @Test
    public void setCapitalDistributionTest() {
        Agent[] agents = init.calculateInitialAgentState();
        init.addGroup(groups[0]);
        init.setGroupDistribution(agentDistributions[3], groups[0]);
        init.setCapitalDistribution(agentDistributions[0], 10, groups[0]);
        init.setCapitalDistribution(agentDistributions[1], 10, groups[0]);
        init.setCapitalDistribution(agentDistributions[2], 20, groups[0]);
        int tenNumber = 0;
        int twNumber = 0;
        for (int i = 0; i < groups[0].getMembers().size(); i++) {
            if (groups[0].getMembers().get(i),getScore() == 10) {
                tenNumber++;
            }
            if (groups[0].getMembers().get(i),getScore() == 20) {
                twNumber++;
            }
        }
        assertTrue(tenNumber == 5);
        assertTrue(twNumber == 5);
        assertTrue(agents[1].getScore() == 10);
        assertTrue(agents[5].getScore() == 10);
        assertTrue(agents[9].getScore() == 10);
    }
}