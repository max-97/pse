package de.sswis.model;

import de.swwis.util.AgentDistribution;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

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
        groups = new Group[2];
        groups[0] = new Group(0, "Group1");
        groups[a] = new Group(1, "Group2");
    }

    @Test
    public void setGroupDistributionTest() {

    }

    @Test
    public void setStrategyDistribution() {

    }

    @Test
    public void setCapitalDistribution() {

    }
}