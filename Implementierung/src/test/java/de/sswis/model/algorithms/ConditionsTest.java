package de.sswis.model.algorithms;

import de.sswis.model.Agent;
import de.sswis.model.Group;
import de.sswis.model.conditions.*;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.HashMap;

import static org.junit.Assert.*;

public class ConditionsTest {

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
        a2 = new Agent(2, 0, group2, null);
        a3 = new Agent(3, 0, group2, null);
    }

    @Test
    public void alwaysTest() {
        Condition always = new Always();
        assertTrue(always.fulfillsCondition(null, null));
    }

    @Test
    public void deltaTest() {
        Condition delta = new Delta();
        HashMap<String, Object> parameters = new HashMap<>();
        parameters.put(delta.getParameters()[0], "10");
        delta.setParameters(parameters);
        a1.setScore(5);
        a2.setScore(14);
        a3.setScore(16);

        assertTrue(delta.fulfillsCondition(a1, a2));
        assertFalse(delta.fulfillsCondition(a1, a3));
    }

    @Test
    public void ownGroupTest() {
        Condition ownGroup = new OwnGroup();

        assertTrue(ownGroup.fulfillsCondition(a2, a3));
        assertFalse(ownGroup.fulfillsCondition(a1, a2));
    }

    @Test
    public void poorerTest() {
        Condition poorer = new Poorer();
        a1.setScore(5);
        a2.setScore(6);
        a3.setScore(16);

        assertTrue(poorer.fulfillsCondition(a2, a1));
        assertFalse(poorer.fulfillsCondition(a1, a3));
    }

    @Test
    public void probabilityTest() {
        Condition probability = new Probability();
        HashMap<String, Object> parameters = new HashMap<>();
        parameters.put(probability.getParameters()[0], "1");
        probability.setParameters(parameters);

        assertTrue(probability.fulfillsCondition(null, null));

        parameters.put(probability.getParameters()[0], "0");
        probability.setParameters(parameters);

        assertFalse(probability.fulfillsCondition(null, null));
    }

    @Test
    public void richerTest() {
        Condition richer = new Richer();

        a1.setScore(5);
        a2.setScore(6);
        a3.setScore(16);

        assertFalse(richer.fulfillsCondition(a2, a1));
        assertTrue(richer.fulfillsCondition(a1, a3));
    }

    @Test
    public void specificGroupTest() {
        Condition specificGroup = new SpecificGroup();
        HashMap<String, Object> parameters = new HashMap<>();
        parameters.put(specificGroup.getParameters()[0], "1");
        specificGroup.setParameters(parameters);

        assertFalse(specificGroup.fulfillsCondition(null, a2));
        assertTrue(specificGroup.fulfillsCondition(null, a1));
    }
}
