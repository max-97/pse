package de.sswis.model.algorithms.adaptation;

import de.sswis.model.Agent;
import de.sswis.model.CombinedStrategy;
import de.sswis.model.Group;
import de.sswis.model.MixedStrategy;
import de.sswis.model.conditions.Always;
import de.sswis.model.conditions.Condition;
import de.sswis.model.strategies.AlwaysCooperate;
import de.sswis.model.strategies.BaseStrategy;
import de.sswis.model.strategies.NeverCooperate;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.HashMap;

import static org.junit.Assert.assertTrue;

public class MixedLinearInterpolationTest {
    private static Agent[] agents;
    private static MixedStrategy strategy1;
    private static MixedStrategy strategy2;
    private static CombinedStrategy alwaysCooperate;
    private static CombinedStrategy neverCooperate;
    private static MixedLinearInterpolation adaptationAlgorithm;
    private static Group group;
    private static HashMap<Agent, Integer> ranking;

    @BeforeClass
    public static void init() {
        adaptationAlgorithm = new MixedLinearInterpolation();
        alwaysCooperate = new CombinedStrategy("1", new BaseStrategy[]{new AlwaysCooperate()},
                new Condition[]{new Always()});
        neverCooperate = new CombinedStrategy("2", new BaseStrategy[]{new NeverCooperate()},
                new Condition[]{new Always()});
        strategy1 = new MixedStrategy("50%", new CombinedStrategy[]{alwaysCooperate, neverCooperate},
                new double[]{0.5, 0.5});
        strategy2 = new MixedStrategy("80%", new CombinedStrategy[]{alwaysCooperate, neverCooperate},
                new double[]{0.8, 0.2});
        ranking = new HashMap<>();
        group = new Group(1, "1");
    }

    @Test
    public void adaptationIsCorrect() {
        agents = new Agent[2];
        agents[0] = new Agent(0, 0, group, strategy1.clone());
        agents[1] = new Agent(1, 100, group, strategy2.clone());
        ranking.put(agents[0], 2);
        ranking.put(agents[1], 1);
        adaptationAlgorithm.adapt(agents, ranking, 1);
        double probability1;
        double probability2;
        MixedStrategy strat = (MixedStrategy)agents[0].getStrategy();
        if(((MixedStrategy)agents[0].getStrategy()).getCombinedStrategies()[0].getName() == "1") {
            probability1 = strat.getProbabilities()[0];
            probability2 = strat.getProbabilities()[1];
        } else {
            probability2 = strat.getProbabilities()[0];
            probability1 = strat.getProbabilities()[1];
        }
        assertTrue((probability1 == 0.5 && probability2 == 0.5 && strat.getAdaptationCount() == 0) ||
                (probability1 == 0.8 && probability2 == 0.2) && strat.getAdaptationCount() == 1);
    }
}
