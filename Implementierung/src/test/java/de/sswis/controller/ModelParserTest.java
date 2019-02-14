package de.sswis.controller;

import de.sswis.model.*;
import de.sswis.model.conditions.*;
import de.sswis.model.strategies.*;
import de.sswis.model.strategies.Random;
import de.sswis.view.model.*;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.*;

import static org.junit.Assert.*;

public class ModelParserTest {

    private static ModelParser modelParser;

    private static VMCombinedStrategy combStrategy1;
    private static VMCombinedStrategy combStrategy2;
    private static VMCombinedStrategy combStrategy3;


    @BeforeClass
    public static void setUp() {

        modelParser = new ModelParser();

        HashMap<String, Object> empty = new HashMap<>();
        combStrategy1 = new VMCombinedStrategy("Always Cooperate", "Desc", AlwaysCooperate.NAME);
        combStrategy1.addConditionParameter(empty);
        combStrategy2 = new VMCombinedStrategy("Cooperate with same group", "Desc", NeverCooperate.NAME);
        combStrategy2.addStrategy(AlwaysCooperate.NAME, OwnGroup.NAME);
        combStrategy2.addConditionParameter(empty);
        HashMap<String, Object> parameters3 = new HashMap<>();
        parameters3.put("DELTA", "5");
        combStrategy3 = new VMCombinedStrategy("Delta 10", "Desc", NeverCooperate.NAME);
        combStrategy3.addStrategy(AlwaysCooperate.NAME, Delta.NAME);
        combStrategy3.addConditionParameter(parameters3);


    }


    @Test
    public void parseVMCombinedStrategyTest() {
        VMCombinedStrategy[] vmCombinedStrategies = new VMCombinedStrategy[]{combStrategy1, combStrategy2, combStrategy3};
        CombinedStrategy[] combinedStrategies = new CombinedStrategy[3];
        for(int i = 0; i < vmCombinedStrategies.length; i++) {
            combinedStrategies[i] = modelParser.parseVMCombinedStrategy(vmCombinedStrategies[i]);
        }

        for(int i = 0; i < vmCombinedStrategies.length; i++) {
            assertEquals(vmCombinedStrategies[i].getName(), combinedStrategies[i].getName());
            for(int j = 0; j < vmCombinedStrategies[i].getStrategies().size(); j++) {
                assertEquals(vmCombinedStrategies[i].getStrategies().get(j),
                        combinedStrategies[i].getStrategies()[j].getName());
                assertEquals(vmCombinedStrategies[i].getConditions().get(j),
                        combinedStrategies[i].getConditions()[j].getName());
            }
        }
    }

    @Test
    public void parseVMConfiguration() {
    }

    @Test
    public void parseVMGameTest() {

    }

    @Test
    public void parseVMInitialization() {
    }

    @Test
    public void parseVMStrategy() {
    }
}