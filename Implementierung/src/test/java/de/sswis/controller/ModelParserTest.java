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
    private static ModelProvider modelProvider;

    private static VMCombinedStrategy combStrategy1;
    private static VMCombinedStrategy combStrategy2;
    private static VMCombinedStrategy combStrategy3;


    @BeforeClass
    public static void setUp() {

        modelParser = new ModelParser();
        modelProvider = ModelProvider.getInstance();


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
        CombinedStrategy[] combinedStrategies = new CombinedStrategy[vmCombinedStrategies.length];

        for(int i = 0; i < vmCombinedStrategies.length; i++) {
            combinedStrategies[i] = modelParser.parseVMCombinedStrategy(vmCombinedStrategies[i]);
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
    public void parseVMStrategyTest() {
        VMStrategy vmStrategy1 = new VMStrategy();
        vmStrategy1.setName("100%");
        vmStrategy1.addStrategy(combStrategy1.getName(), "1");
        VMStrategy vmStrategy2 = new VMStrategy();
        vmStrategy2.setName("60%/40%");
        vmStrategy2.addStrategy(combStrategy1.getName(), "0.6");
        vmStrategy2.addStrategy(combStrategy2.getName(), "0.4");

        modelProvider.addCombinedStrategy(new CombinedStrategy(combStrategy1.getName(),
                new BaseStrategy[]{new AlwaysCooperate()}, new Condition[]{new Always()}));
        modelProvider.addCombinedStrategy(new CombinedStrategy(combStrategy2.getName(),
                new BaseStrategy[]{new AlwaysCooperate(), new NeverCooperate()},
                new Condition[]{new OwnGroup(), new Always()}));

        VMStrategy[] vmStrategies = new VMStrategy[]{vmStrategy1, vmStrategy2};
        MixedStrategy[] mixedStrategies = new MixedStrategy[vmStrategies.length];

        for(int i = 0; i < vmStrategies.length; i++) {
            mixedStrategies[i] = modelParser.parseVMStrategy(vmStrategies[i]);
            assertEquals(vmStrategies[i].getName(), mixedStrategies[i].getName());
            for(int j = 0; j < vmStrategies[i].getCombinedStrategies().size(); j++) {
                assertEquals(vmStrategies[i].getCombinedStrategies().get(j),
                        mixedStrategies[i].getCombinedStrategies()[j].getName());
                assertEquals(Double.parseDouble(vmStrategies[i].getProbabilities().get(j)),
                        mixedStrategies[i].getProbabilities()[j], 0.0001);
            }
        }
    }

    @Test
    public void parseSimpleVMConfigurationTest() {
        HashMap<String, Object> empty = new HashMap<>();
        VMInitialization vmInit = new VMInitialization();
        vmInit.setName("Init");
        modelProvider.addInitialization(new Initialization("Init", 100));
        modelProvider.addGame(new Game("Game", "", null));

        VMConfiguration vmConfig = new VMConfiguration();
        vmConfig.setName("Config");
        vmConfig.setRounds("100");
        vmConfig.setCycles("10");
        vmConfig.setGame("Game");
        vmConfig.setAdaptationAlg("Replicator Dynamic Score");
        vmConfig.setRankingAlg("Gesamtpunktzahl");
        vmConfig.setPairingAlg("Zufällige Paarung");
        vmConfig.setPairingParameters(empty);
        vmConfig.setRankingParameters(empty);
        vmConfig.setAdaptationParameters(empty);
        vmConfig.setInit("Init");
        vmConfig.setAdaptationProbability("1");
        vmConfig.setEquilibriumRounds(15);
        vmConfig.setEquilibriumMaxChange(10);

        Configuration result = null;
        for(Configuration config : modelParser.parseVMConfiguration(vmConfig)) {
            result = config;
        }

        Object[] expecteds = new Object[]{"Config", 1000, 10, "Game", "Replicator Dynamic Score", "Gesamtpunktzahl",
                "Zufällige Paarung", "Init", 1.0, 15, 0.1};
        Object[] actuals = new Object[]{result.getName(), result.getRounds(), result.getCycles(),
                result.getGame().getName(), result.getAdaptationAlg().getName(), result.getRankingAlg().getName(),
                result.getPairingAlg().getName(), result.getInit().getName(), result.getAdaptationProbability(),
                result.getEquilibriumRounds(), result.getThreshold()};
        assertArrayEquals(expecteds, actuals);
    }

    @Test
    public void parseSimpleVMInitialization() {

    }

}