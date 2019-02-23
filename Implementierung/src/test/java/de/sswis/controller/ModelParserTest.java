package de.sswis.controller;

import de.sswis.model.*;
import de.sswis.model.conditions.*;
import de.sswis.model.strategies.*;
import de.sswis.model.strategies.Random;
import de.sswis.util.AgentDistribution;
import de.sswis.view.model.*;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.*;

import static org.junit.Assert.*;

public class ModelParserTest {

    private static FileManager fileManager;
    private static ModelParser modelParser;
    private static ModelProvider modelProvider;

    private static VMCombinedStrategy combStrategy1;
    private static VMCombinedStrategy combStrategy2;
    private static VMCombinedStrategy combStrategy3;


    @BeforeClass
    public static void setUp() {
        fileManager = new FileManager();
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
        vmInit.setName("Init_Test1");
        try {
            fileManager.saveInitialization(vmInit);
        } catch( Exception e) {
            e.printStackTrace();
        }
        modelProvider.addInitialization(new Initialization("Init_Test1", 100));
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
        vmConfig.setInit("Init_Test1");
        vmConfig.setAdaptationProbability("1");
        vmConfig.setEquilibriumRounds(15);
        vmConfig.setEquilibriumMaxChange(10);

        Configuration result = null;
        for(Configuration config : modelParser.parseVMConfiguration(vmConfig)) {
            result = config;
        }

        Object[] expecteds = new Object[]{"Config", 1000, 10, "Game", "Replicator Dynamic Score", "Gesamtpunktzahl",
                "Zufällige Paarung", "Init_Test1", 1.0, 15, 0.1};
        Object[] actuals = new Object[]{result.getName(), result.getRounds(), result.getCycles(),
                result.getGame().getName(), result.getAdaptationAlg().getName(), result.getRankingAlg().getName(),
                result.getPairingAlg().getName(), result.getInit().getName(), result.getAdaptationProbability(),
                result.getEquilibriumRounds(), result.getThreshold()};
        assertArrayEquals(expecteds, actuals);
    }

    @Test
    public void parseSimpleVMInitializationWithIDs() {
        VMGroup group1 = new VMGroup();
        group1.setId(1);
        group1.setName("1");
        group1.setAgents("0-4,6");
        group1.setRelativeStrategyDistribution(false);
        group1.addStrategy("Always Cooperate", "0-4");
        group1.addStrategy("Delta 10", "6");
        group1.setRelativeCapitalDistributions(false);
        group1.addStartCapital("100", "4,3,2,1,0,6");
        VMGroup group2 = new VMGroup();
        group2.setId(2);
        group2.setName("2");
        group2.setAgents("5,7-9");
        group2.setRelativeStrategyDistribution(false);
        group2.addStrategy("Always Cooperate", "7-9,5");
        group2.setRelativeCapitalDistributions(false);
        group2.addStartCapital("150", "7-8");
        group2.addStartCapital("200", "9,5");
        VMInitialization vmInit = new VMInitialization();
        vmInit.setName("Init_Test2");
        vmInit.setAgentCount(10);
        vmInit.addGroup(group1);
        vmInit.addGroup(group2);
        vmInit.setAddCapitalToTotalPoints(false);
        vmInit.setRelativeDistribution(false);

        Initialization result = null;
        for(Initialization init : modelParser.parseVMInitialization(vmInit)) {
            result = init;
        }

        List<AgentDistribution> groupDistribution = result.getGroupAgentDistributions();
        List<AgentDistribution> strategyDistribution = result.getStrategyAgentDistributions();
        List<AgentDistribution> capitalDistribution = result.getCapitalAgentDistributions();

        Object[] expecteds = new Object[]{"Init_Test2", 10, true, new int[]{0, 1, 2, 3, 4, 6}, new int[]{5, 7, 8, 9},
                new int[]{0, 1, 2, 3, 4}, new int[]{6}, new int[]{5, 7, 8, 9}, new int[]{0, 1, 2, 3, 4, 6},
                new int[]{7, 8}, new int[]{5, 9}};
        Object[] actuals = new Object[]{result.getName(), result.getAgentCount(), result.getInitialScoreStrategiesOnly(),
                groupDistribution.get(0).getAgentIDs(), groupDistribution.get(1).getAgentIDs(),
                strategyDistribution.get(0).getAgentIDs(), strategyDistribution.get(1).getAgentIDs(),
                strategyDistribution.get(2).getAgentIDs(), capitalDistribution.get(0).getAgentIDs(),
                capitalDistribution.get(1).getAgentIDs(), capitalDistribution.get(2).getAgentIDs()};
        assertArrayEquals(expecteds, actuals);
    }

    @Test
    public void parseSimpleVMInitializationWithRelativeDistribution() {
        VMInitialization vmInit = new VMInitialization();
        vmInit.setName("Init_Test3");
        vmInit.setAddCapitalToTotalPoints(false);
        vmInit.setRelativeDistribution(true);
    }

    @Test
    public void parseVMInitializationWithVariableGroupDistribution() {

    }

    @Test
    public void parseVMInitializationWithVariableStrategyDistribution() {

    }

    @Test
    public void parseVMInitializationWithVariableCapitalDistribution() {

    }

    @Test
    public void parseVMInitializationWithVariableCapital() {

    }
}