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
import de.sswis.util.AgentDistribution;
import org.junit.Test;

import static org.junit.Assert.*;


public class SimulationTest {

    @Test
    public void test() {
        CombinedStrategy strategy = new CombinedStrategy("Strategy", new BaseStrategy[]{new AlwaysCooperate()},
                new Condition[]{new Always()});
        Group group = new Group(1,"Group");
        AgentDistribution distribution = new AgentDistribution(100);
        Initialization init = new Initialization("Init", 10);
        init.addGroup(group);
        init.setGroupDistribution(distribution, group);
        init.setStrategyDistribution(distribution, strategy, group);

        Game.Tuple[][] payoffs = new Game.Tuple[2][2];
        payoffs[0][0] = new Game.Tuple(10, 10);
        payoffs[1][1] = new Game.Tuple(0, 0);
        payoffs[0][1] = new Game.Tuple(-5, 5);
        payoffs[1][0] = new Game.Tuple(5, -5);
        Game game = new Game("Game", "", payoffs);
        AdaptationAlgorithm adaptationAlgorithm = new ReplicatorDynamicScore();
        RankingAlgorithm rankingAlgorithm = new Score();
        PairingAlgorithm pairingAlgorithm = new RandomPairing();
        Configuration config = new Configuration("Config", game, init, adaptationAlgorithm, pairingAlgorithm,
                rankingAlgorithm, 100, 5, 1);
        Simulation sim = config.simulate();
        sim.run();
        Result result = sim.getResults();
        assertEquals(1, result.getAgents().size());

        for(Agent agent: result.getAgents().get(1)) {
            assertTrue(agent.getHistory().getAlwaysCooperated());
            assertTrue(agent.getHistory().groupCooperatedEveryTime(group));
            assertTrue(agent.getHistory().groupCooperatedEveryTime(group));
        }
    }
}
