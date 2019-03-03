package de.sswis.model;

import de.sswis.model.algorithms.adaptation.AdaptationAlgorithm;
import de.sswis.model.algorithms.adaptation.ReplicatorDynamicScore;
import de.sswis.model.algorithms.pairing.PairingAlgorithm;
import de.sswis.model.algorithms.pairing.RandomPairing;
import de.sswis.model.algorithms.ranking.RankingAlgorithm;
import de.sswis.model.algorithms.ranking.Score;
import de.sswis.model.conditions.Always;
import de.sswis.model.conditions.Condition;
import de.sswis.model.strategies.AlwaysCooperate;
import de.sswis.model.strategies.BaseStrategy;
import de.sswis.model.strategies.NeverCooperate;
import de.sswis.util.AgentDistribution;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.*;


public class SimulationTest {

    private static Game game;
    private static CombinedStrategy combinedStrategy1;
    private static CombinedStrategy combinedStrategy2;
    private static MixedStrategy mixedStrategy1;
    private static RankingAlgorithm rankingAlgorithm;
    private static AdaptationAlgorithm adaptationAlgorithm;
    private static PairingAlgorithm pairingAlgorithm;


    @BeforeClass
    public static void init() {
        Game.Tuple[][] payoffs = new Game.Tuple[2][2];
        payoffs[0][0] = new Game.Tuple(10, 10);
        payoffs[1][1] = new Game.Tuple(0, 0);
        payoffs[0][1] = new Game.Tuple(-5, 5);
        payoffs[1][0] = new Game.Tuple(5, -5);
        game = new Game("Game", "", payoffs);
        combinedStrategy1 = new CombinedStrategy("Strategy", new BaseStrategy[]{new AlwaysCooperate()},
                new Condition[]{new Always()});
        combinedStrategy2 = new CombinedStrategy("Strategy2", new BaseStrategy[]{new NeverCooperate()},
                new Condition[]{new Always()});
        mixedStrategy1 = new MixedStrategy("Mixed Strategy", new CombinedStrategy[]{combinedStrategy1,
                combinedStrategy2}, new double[]{0.5, 0.5});
        adaptationAlgorithm = new ReplicatorDynamicScore();
        rankingAlgorithm = new Score();
        pairingAlgorithm = new RandomPairing();
    }

    @Test
    public void testCombinedStrategy() {
        Group group = new Group(1,"Group");
        AgentDistribution distribution = new AgentDistribution(100);
        Initialization init = new Initialization("Init", 10);
        init.addGroup(group);
        init.setGroupDistribution(distribution, group);
        init.setStrategyDistribution(distribution, combinedStrategy1, group);
        Configuration config = new Configuration("Config", game, init, adaptationAlgorithm, pairingAlgorithm,
                rankingAlgorithm, 100, 5, 1, 100, 0.2);
        Simulation sim = config.simulate();
        sim.run();
        Result result = sim.getResults();

        for(Agent agent: result.getAgents().get(1)) {
            assertTrue(agent.getHistory().getAlwaysCooperated());
            assertTrue(agent.getHistory().groupCooperatedEveryTime(group));
            assertTrue(agent.getHistory().groupCooperatedEveryTime(group));
        }
    }

    @Test
    public void testMixedStrategy() {
        Group group = new Group(1,"Group");
        AgentDistribution distribution = new AgentDistribution(100);
        Initialization init = new Initialization("Init", 10);
        init.addGroup(group);
        init.setGroupDistribution(distribution, group);
        init.setStrategyDistribution(distribution, mixedStrategy1, group);
        Configuration config = new Configuration("Config", game, init, adaptationAlgorithm, pairingAlgorithm,
                rankingAlgorithm, 100, 5, 1, 100, 0.2);
        Simulation sim = config.simulate();
        sim.run();
    }

    @Test
    public void testRepetitions() {
        Group group = new Group(1,"Group");
        AgentDistribution distribution = new AgentDistribution(100);
        Initialization init = new Initialization("Init", 10);
        init.addGroup(group);
        init.setGroupDistribution(distribution, group);
        init.setStrategyDistribution(distribution, combinedStrategy1, group);
        Configuration config = new Configuration("Config", game, init, adaptationAlgorithm, pairingAlgorithm,
                rankingAlgorithm, 100, 5, 1,100, 0.2);
        Simulation sim = config.simulate();
        sim.setRepetitions(10);
        sim.run();
        Result result = sim.getResults();
        assertEquals(10, result.getAgents().size());
        assertEquals(10, result.getEquilibriums().size());
    }

    @Test
    public void equilibriumTest() {
        Group group = new Group(1,"Group");
        AgentDistribution distribution = new AgentDistribution(100);
        Initialization init = new Initialization("Init", 10);
        init.addGroup(group);
        init.setGroupDistribution(distribution, group);
        init.setStrategyDistribution(distribution, combinedStrategy1, group);
        Configuration config = new Configuration("Config", game, init, adaptationAlgorithm, pairingAlgorithm,
                rankingAlgorithm, 100, 5, 0, 2, 0.2);
        Simulation sim = config.simulate();
        sim.run();
        Result result = sim.getResults();

        assertTrue(result.getEquilibriums().get(1));
    }
}
