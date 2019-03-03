package de.sswis.model;

import de.sswis.model.strategies.*;
import de.sswis.model.strategies.Random;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.*;

public class StrategiesTest {

    private static Action c = Action.COOPERATION;
    private static Action d = Action.DEFECTION;
    private static Group group1;
    private static Group group2;
    private static Agent a1;
    private static Agent a2;
    private static Agent a3;

    @BeforeClass
    public static void init() {
        group1 = new Group(1, "1");
        group2 = new Group(2, "2");
        a1 = new Agent(1, 0, group1, null);
        a2 = new Agent(1, 0, group1, null);
        a3 = new Agent(1, 0, group2, null);
    }

    @Test
    public void alwaysCooperateTest() {
        BaseStrategy  s = new AlwaysCooperate();
        assertEquals(c, s.calculateAction(null, null));
    }

    @Test
    public void neverCooperateTest() {
        BaseStrategy  s = new NeverCooperate();
        assertEquals(d, s.calculateAction(null, null));
    }

    @Test
    public void randomTest() {
        BaseStrategy  s = new Random();
        Action action = s.calculateAction(null, null);
        assertTrue(action == c || action == d);
    }

    @Test
    public void grimEverybodyTest() {
        BaseStrategy  s = new GrimEverybody();
        a1.getHistory().setAlwaysCooperated(true);
        a2.getHistory().setAlwaysCooperated(false);

        assertEquals(c, s.calculateAction(a3, a1));
        assertEquals(d, s.calculateAction(a3, a2));
    }

    @Test
    public void grimIndividualTest() {
        BaseStrategy  s = new GrimIndividual();
        a3.getHistory().setCooperatedEveryTime(a1, true);
        a3.getHistory().setCooperatedEveryTime(a2, false);

        assertEquals(c, s.calculateAction(a3, a1));
        assertEquals(d, s.calculateAction(a3, a2));
    }

    @Test
    public void groupGrimTest() {
        BaseStrategy  s = new GroupGrim();
        a1.getHistory().setGroupCooperatedEveryTime(group2, true);
        a2.getHistory().setGroupCooperatedEveryTime(group2, false);

        assertEquals(c, s.calculateAction(a3, a1));
        assertEquals(d, s.calculateAction(a3, a2));
    }

    @Test
    public void titForTatEverybodyTest() {
        BaseStrategy  s = new TitForTatEverybody();
        a3.getHistory().setOpponentCooperated(true);

        assertEquals(c, s.calculateAction(a3, a1));

        a3.getHistory().increaseRoundCount();
        a3.getHistory().setOpponentCooperated(false);

        assertEquals(d, s.calculateAction(a3, a2));
    }

    @Test
    public void titForTatIndividualTest() {
        BaseStrategy  s = new TitForTatIndividual();
        a3.getHistory().setCooperatedLastTime(a1, true);
        a3.getHistory().setCooperatedLastTime(a2, false);

        assertEquals(c, s.calculateAction(a3, a1));
        assertEquals(d, s.calculateAction(a3, a2));
    }

    @Test
    public void groupTitForTatTest() {
        BaseStrategy  s = new GroupTitForTat();
        a1.getHistory().setGroupCooperatedLastTime(group2, true);
        a2.getHistory().setGroupCooperatedLastTime(group2, false);

        assertEquals(c, s.calculateAction(a3, a1));
        assertEquals(d, s.calculateAction(a3, a2));
    }
}
