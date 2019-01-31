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
import static org.junit.Assert.*;

public class CombinedStrategyTest {
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
        agents[0].setStrategy(strategy);
        agents[1].setStrategy(strategy);
        assertEquals(Action.COOPERATION, agents[0].getStrategy().calculateAction(agents[0], agents[1]));
    }

    @Test
    public void calculateActionTest2() {
        CombinedStrategy strategy = new CombinedStrategy("AlwaysCooperateSameGroup",
                new BaseStrategy[]{new AlwaysCooperate()}, new Condition[]{new OwnGroup()});
        agents[0].setStrategy(strategy);
        agents[1].setStrategy(strategy);
        Group group1 = new Group(1, "1");
        Group group2 = new Group(2, "2");
        agents[0].setGroup(group1);
        agents[1].setGroup(group1);
        assertEquals(Action.COOPERATION, agents[0].getStrategy().calculateAction(agents[0], agents[1]));
        agents[1].setGroup(group2);
        assertEquals(Action.DEFECTION, agents[0].getStrategy().calculateAction(agents[0], agents[1]));
    }

    @Test
    public void calculateActionTest3() {
        CombinedStrategy strategy = new CombinedStrategy("CooperateWhenRicher",
                new BaseStrategy[]{new AlwaysCooperate()}, new Condition[]{new Richer()});
        agents[0].setStrategy(strategy);
        agents[1].setStrategy(strategy);
        agents[0].setScore(100);
        agents[1].setScore(101);
        assertEquals(Action.COOPERATION, agents[0].getStrategy().calculateAction(agents[0], agents[1]));
        agents[1].setScore(99);
        assertEquals(Action.DEFECTION, agents[0].getStrategy().calculateAction(agents[0], agents[1]));
    }

    @Test
    public void calculateActionTest4() {
        CombinedStrategy strategy = new CombinedStrategy("TitForTatIndividual",
                new BaseStrategy[]{new TitForTatIndividual()}, new Condition[]{new Always()});
        agents[0].setStrategy(strategy);
        agents[1].setStrategy(strategy);
        assertEquals(Action.COOPERATION, agents[0].getStrategy().calculateAction(agents[0], agents[1]));
        agents[0].getHistory().setCooperatedLastTime(agents[1], false);
        assertEquals(Action.DEFECTION, agents[0].getStrategy().calculateAction(agents[0], agents[1]));
    }
}
