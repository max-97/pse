package de.sswis.model;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import de.sswis.model.conditions.Always;
import de.sswis.model.conditions.Condition;
import de.sswis.model.strategies.AlwaysCooperate;
import de.sswis.model.strategies.BaseStrategy;
import de.sswis.model.strategies.NeverCooperate;
import de.sswis.util.AgentDistribution;

public class InitializationTest {
    private static AgentDistribution[] agentDistributions;
    private static int[] ids;
    private Initialization init;
    private Group[] groups;
    private Strategy always;
    private Strategy never;
    private Agent[] agents;

    @Before
    public void init() {
        init = new Initialization("init", 20);
        ids = new int[3];
        ids[0] = 1;
        ids[1] = 20;
        agentDistributions = new AgentDistribution[4];
        agentDistributions[0] = new AgentDistribution(ids);
        agentDistributions[1] = new AgentDistribution(45);
        agentDistributions[2] = new AgentDistribution(55);
        agentDistributions[3] = new AgentDistribution(100);
        groups = new Group[2];
        groups[0] = new Group(0, "Group1");
        groups[1] = new Group(1, "Group2");

        init.addGroup(groups[0]);
        init.addGroup(groups[1]);
        init.setGroupDistribution(agentDistributions[0], groups[0]);
        init.setGroupDistribution(agentDistributions[1], groups[0]);
        init.setGroupDistribution(agentDistributions[2], groups[1]);

        always = new CombinedStrategy("AlwaysCooperate",
                new BaseStrategy[]{new AlwaysCooperate()}, new Condition[]{new Always()});
        never = new CombinedStrategy("NeverCooperate",
                new BaseStrategy[]{new NeverCooperate()}, new Condition[]{new Always()});
        init.setStrategyDistribution(agentDistributions[0], always, groups[0]);
        init.setStrategyDistribution(agentDistributions[1], never, groups[0]);
        init.setStrategyDistribution(agentDistributions[2], always, groups[0]);
        init.setStrategyDistribution(agentDistributions[3], never, groups[1]);

        init.setCapitalDistribution(agentDistributions[0], 10, groups[0]);
        init.setCapitalDistribution(agentDistributions[1], 20, groups[0]);
        init.setCapitalDistribution(agentDistributions[1], 10, groups[0]);
        init.setCapitalDistribution(agentDistributions[3], 20, groups[1]);

        agents = init.calculateInitialAgentState();
    }

    @Test
    public void setGroupDistributionTest() {
        assertTrue(groups[0].getMembers().size() == 9);
        assertTrue(groups[1].getMembers().size() == 11);
        assertTrue(groups[0].getMembers().contains(agents[1]));
        assertTrue(groups[0].getMembers().contains(agents[20]));
    }

    @Test
    public void setStrategyDistributionTest() {
        int alwaysNumber = 0;
        int neverNumber = 0;
        for (int i = 0; i < groups[0].getMembers().size(); i++) {
            if (groups[0].getMembers().get(i).getStrategy() == always) {
                alwaysNumber++;
            }
            if (groups[0].getMembers().get(i).getStrategy() == never) {
                neverNumber++;
            }
        }
        assertTrue(alwaysNumber == 5);
        assertTrue(neverNumber == 4);
        assertEquals(always, agents[1].getStrategy());
        assertEquals(never, agents[9].getStrategy());
        assertEquals(always, agents[20].getStrategy());
    }

    @Test
    public void setCapitalDistributionTest() {
        int tenNumber = 0;
        int twNumber = 0;
        for (int i = 0; i < groups[0].getMembers().size(); i++) {
            if (groups[0].getMembers().get(i).getScore() == 10) {
                tenNumber++;
            }
            if (groups[0].getMembers().get(i).getScore() == 20) {
                twNumber++;
            }
        }
        assertTrue(tenNumber == 5);
        assertTrue(twNumber == 4);
        assertTrue(agents[1].getScore() == 10);
        assertTrue(agents[9].getScore() == 20);
        assertTrue(agents[20].getScore() == 10);
    }
}