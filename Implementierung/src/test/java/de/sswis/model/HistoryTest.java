package de.sswis.model;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class HistoryTest {
    private static Agent agent1;
    private static Agent agent2;

    @Before
    public void init() {
        Group group1 = new Group(1, "Group 1");
        Group group2 = new Group(2, "Group 2");
        agent1 = new Agent(1, 0, group1, null);
        agent2 = new Agent(1, 0, group2, null);
    }

    @Test
    public void cooperatedLastTimeTest() {
        assertTrue(agent1.getHistory().cooperatedLastTime(agent2));
        assertTrue(agent1.getHistory().cooperatedLastTime(agent2));
        agent1.getHistory().setCooperatedLastTime(agent2, false);
        assertFalse(agent1.getHistory().cooperatedLastTime(agent2));
    }

    @Test
    public void cooperatedEveryTimeTest() {
        assertTrue(agent1.getHistory().cooperatedEveryTime(agent2));
        assertTrue(agent1.getHistory().cooperatedEveryTime(agent2));
        agent1.getHistory().setCooperatedEveryTime(agent2, false);
        assertFalse(agent1.getHistory().cooperatedEveryTime(agent2));
    }

    @Test
    public void groupCooperatedLastTimeTest() {
        assertTrue(agent1.getHistory().groupCooperatedLastTime(agent2.getGroup()));
        assertTrue(agent1.getHistory().groupCooperatedLastTime(agent2.getGroup()));
        agent1.getHistory().setGroupCooperatedLastTime(agent2.getGroup(), false);
        assertFalse(agent1.getHistory().groupCooperatedLastTime(agent2.getGroup()));
    }

    @Test
    public void groupCooperatedEveryTimeTest() {
        assertTrue(agent1.getHistory().groupCooperatedEveryTime(agent2.getGroup()));
        assertTrue(agent1.getHistory().groupCooperatedEveryTime(agent2.getGroup()));
        agent1.getHistory().setGroupCooperatedEveryTime(agent2.getGroup(), false);
        assertFalse(agent1.getHistory().groupCooperatedEveryTime(agent2.getGroup()));
    }
}
