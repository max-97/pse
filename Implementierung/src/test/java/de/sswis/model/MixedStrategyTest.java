package de.sswis.model;

import de.sswis.model.conditions.Always;
import de.sswis.model.conditions.Condition;
import de.sswis.model.conditions.OwnGroup;
import de.sswis.model.conditions.Richer;
import de.sswis.model.strategies.AlwaysCooperate;
import de.sswis.model.strategies.BaseStrategy;
import de.sswis.model.strategies.TitForTatIndividual;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class MixedStrategyTest {
    private Agent[] agents;

    @Before
    public void init() {
        agents = new Agent[2];
        agents[0] = new Agent(1, 0 , null, null);
        agents[1] = new Agent(2, 0 , null,  null);
    }

    @Test
    public void calculateActionTest1() {
        CombinedStrategy strategy = new CombinedStrategy("AlwaysCooperate",
                new BaseStrategy[]{new AlwaysCooperate()}, new Condition[]{new Always()});
        MixedStrategy mixedStrategy = new MixedStrategy(strategy.getName(), new CombinedStrategy[]{strategy},
                new double[]{1.0});
        agents[0].setStrategy(mixedStrategy);
        agents[1].setStrategy(mixedStrategy);
        assertEquals(Action.COOPERATION, agents[0].getStrategy().calculateAction(agents[0], agents[1]));
    }

    @Test
    public void calculateActionTest2() {
        CombinedStrategy strategy1 = new CombinedStrategy("AlwaysCooperate",
                new BaseStrategy[]{new AlwaysCooperate()}, new Condition[]{new Always()});
        CombinedStrategy strategy2 = new CombinedStrategy("AlwaysCooperateSameGroup",
                new BaseStrategy[]{new AlwaysCooperate()}, new Condition[]{new OwnGroup()});
        MixedStrategy mixedStrategy = new MixedStrategy("Mixed Strategy",
                new CombinedStrategy[]{strategy1, strategy2}, new double[]{0.5, 0.5});
        agents[0].setStrategy(mixedStrategy);
        agents[1].setStrategy(mixedStrategy);
        Group group1 = new Group(1, "1");
        agents[0].setGroup(group1);
        agents[1].setGroup(group1);
        assertEquals(Action.COOPERATION, agents[0].getStrategy().calculateAction(agents[0], agents[1]));
    }
}
