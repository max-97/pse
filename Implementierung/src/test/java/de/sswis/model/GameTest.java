package de.sswis.model;

import de.sswis.model.conditions.Always;
import de.sswis.model.conditions.Condition;
import de.sswis.model.strategies.AlwaysCooperate;
import de.sswis.model.strategies.BaseStrategy;
import de.sswis.model.strategies.NeverCooperate;
import org.junit.Before;
import org.junit.Test;

import static  org.junit.Assert.*;

public class GameTest {
    private Game game;
    private Agent agent1;
    private Agent agent2;

    @Before
    public void init() {
        Group group1 = new Group(1, "1");
        agent1 = new Agent(1, 0,group1, null);
        agent2 = new Agent(2, 0,group1, null);
        Game.Tuple[][] payoffs = new Game.Tuple[2][2];
        payoffs[0][0] = new Game.Tuple(10, 10);
        payoffs[1][1] = new Game.Tuple(0, 0);
        payoffs[0][1] = new Game.Tuple(-5, 5);
        payoffs[1][0] = new Game.Tuple(5, -5);
        game = new Game("Game1", "", payoffs);
    }

    @Test
    public void playGameTest() {
        CombinedStrategy strategy1 = new CombinedStrategy("AlwaysCooperate",
                new BaseStrategy[]{new AlwaysCooperate()}, new Condition[]{new Always()});
        agent1.setStrategy(strategy1);
        agent2.setStrategy(strategy1);
        Pair pair = new Pair(agent1, agent2);
        game.playGame(pair);
        assertEquals(10, agent1.getScore());
        assertEquals(10, agent2.getScore());
        assertTrue(agent1.getHistory().getAlwaysCooperated());
        assertTrue(agent2.getHistory().getAlwaysCooperated());
        assertTrue(agent1.getHistory().cooperatedEveryTime(agent2));
        assertTrue(agent2.getHistory().cooperatedEveryTime(agent1));
        assertTrue(agent1.getHistory().cooperatedLastTime(agent2));
        assertTrue(agent2.getHistory().cooperatedLastTime(agent1));
        assertTrue(agent1.getHistory().groupCooperatedEveryTime(agent2.getGroup()));
        assertTrue(agent1.getHistory().groupCooperatedLastTime(agent2.getGroup()));
        assertTrue(agent2.getHistory().groupCooperatedEveryTime(agent1.getGroup()));
        assertTrue(agent2.getHistory().groupCooperatedLastTime(agent1.getGroup()));

        CombinedStrategy strategy2 = new CombinedStrategy("NeverCooperate",
                new BaseStrategy[]{new NeverCooperate()}, new Condition[]{new Always()});
        agent2.setStrategy(strategy2);
        game.playGame(pair);
        assertEquals(5, agent1.getScore());
        assertEquals(15, agent2.getScore());
        assertTrue(agent1.getHistory().getAlwaysCooperated());
        assertFalse(agent2.getHistory().getAlwaysCooperated());
        assertFalse(agent1.getHistory().cooperatedEveryTime(agent2));
        assertTrue(agent2.getHistory().cooperatedEveryTime(agent1));
        assertFalse(agent1.getHistory().cooperatedLastTime(agent2));
        assertTrue(agent2.getHistory().cooperatedLastTime(agent1));
        assertTrue(agent1.getHistory().groupCooperatedEveryTime(agent2.getGroup()));
        assertTrue(agent1.getHistory().groupCooperatedLastTime(agent2.getGroup()));
        assertFalse(agent2.getHistory().groupCooperatedEveryTime(agent1.getGroup()));
        assertFalse(agent2.getHistory().groupCooperatedLastTime(agent1.getGroup()));
    }
}
