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

    private static Simulation simulation1;
    private static Simulation simulation2;
    private static VMResult result1;
    private static VMResult result2;

    private static VMCombinedStrategy vmCombStrat1;
    private static VMCombinedStrategy vmCombStrat2;
    private static VMCombinedStrategy vmCombStrat3;
    private static VMCombinedStrategy vmCombStrat4;
    private static VMCombinedStrategy vmCombStrat5;
    private static VMCombinedStrategy vmCombStrat6;
    private static CombinedStrategy parsedCombStrat1;
    private static CombinedStrategy parsedCombStrat2;
    private static CombinedStrategy parsedCombStrat3;
    private static CombinedStrategy parsedCombStrat4;
    private static CombinedStrategy parsedCombStrat5;
    private static CombinedStrategy parsedCombStrat6;
    private static CombinedStrategy targetCombStrat1;
    private static CombinedStrategy targetCombStrat2;
    private static CombinedStrategy targetCombStrat3;
    private static CombinedStrategy targetCombStrat4;
    private static CombinedStrategy targetCombStrat5;
    private static CombinedStrategy targetCombStrat6;

    private static VMConfiguration vmConfig1;
    private static VMConfiguration vmConfig2;
    private static List<Configuration> targetConfigs1;
    private static List<Configuration> targetConfigs2;

    private static VMGame vmGame1;
    private static VMGame vmGame2;
    private static Game parsedGame1;
    private static Game parsedGame2;
    private static Game targetGame1;
    private static Game targetGame2;

    private static VMInitialization vmInit1;
    private static VMInitialization vmInit2;
    private static Initialization targetInit1;
    private static Initialization targetInit2;

    private static VMStrategy vmStrat1;
    private static VMStrategy vmStrat2;
    private static Strategy targetStrat1;
    private static Strategy targetStrat2;


    @BeforeClass
    public static void setUp() {

        modelParser = new ModelParser();

        vmCombStrat1 = new VMCombinedStrategy("AlwaysCooperate", "", AlwaysCooperate.NAME);
        vmCombStrat2 = new VMCombinedStrategy("AlwaysCooperateSameGroup", "", /*dummy*/ Random.NAME,
                new ArrayList<>(Collections.singletonList(AlwaysCooperate.NAME)),
                new ArrayList<>(Collections.singletonList(OwnGroup.NAME)),
                emptyParamList(1));
        vmCombStrat3 = new VMCombinedStrategy("CooperateWhenRicher", "", /*dummy*/ Random.NAME,
                new ArrayList<>(Collections.singletonList(AlwaysCooperate.NAME)),
                new ArrayList<>(Collections.singletonList(Richer.NAME)),
                emptyParamList(1));
        vmCombStrat4 = new VMCombinedStrategy("TitForTatIndividual", "", TitForTatIndividual.NAME);
        vmCombStrat5 = new VMCombinedStrategy("SeveralStratsAndConditionsNoParams", "nichtleer", GroupTitForTat.NAME,
                new ArrayList<>(Arrays.asList(AlwaysCooperate.NAME, GrimIndividual.NAME)),
                new ArrayList<>(Arrays.asList(OwnGroup.NAME, Richer.NAME)),
                emptyParamList(2));
        HashMap<String, Object> cond1Param = new HashMap<>();
        cond1Param.put("DELTA", 3.14159265359d); //TODO: check param-names
        HashMap<String, Object> cond2Param = new HashMap<>();
        cond2Param.put("GROUP_ID", 2); //TODO: check param-names
        List<HashMap<String, Object>> conditionParameters = new ArrayList<>();
        conditionParameters.add(cond1Param);
        conditionParameters.add(cond2Param);
        vmCombStrat6 =new VMCombinedStrategy("ConditionWithParam", "", Random.NAME,
                new ArrayList<>(Arrays.asList(NeverCooperate.NAME, TitForTatIndividual.NAME)),
                new ArrayList<>(Arrays.asList(Delta.NAME, SpecificGroup.NAME)),
                conditionParameters);

        parsedCombStrat1 = modelParser.parseVMCombinedStrategy(vmCombStrat1);
        parsedCombStrat2 = modelParser.parseVMCombinedStrategy(vmCombStrat2);
        parsedCombStrat3 = modelParser.parseVMCombinedStrategy(vmCombStrat3);
        parsedCombStrat4 = modelParser.parseVMCombinedStrategy(vmCombStrat4);
        parsedCombStrat5 = modelParser.parseVMCombinedStrategy(vmCombStrat5);
        //parsedCombStrat6 = modelParser.parseVMCombinedStrategy(vmCombStrat6);

        //1-4 aus CombinedStrategyTest
        targetCombStrat1 = new CombinedStrategy("AlwaysCooperate",
                new BaseStrategy[]{new AlwaysCooperate()}, new Condition[]{new Always()});
        targetCombStrat2 = new CombinedStrategy("AlwaysCooperateSameGroup",
                new BaseStrategy[]{new AlwaysCooperate(), /*dummy*/ new Random()},
                new Condition[]{new OwnGroup(), /*dummy*/ new Always()});
        targetCombStrat3 = new CombinedStrategy("CooperateWhenRicher",
                new BaseStrategy[]{new AlwaysCooperate(), /*dummy*/ new Random()},
                new Condition[]{new Richer(), /*dummy*/ new Always()});
        targetCombStrat4 = new CombinedStrategy("TitForTatIndividual",
                new BaseStrategy[]{new TitForTatIndividual()}, new Condition[]{new Always()});
        //Mehrere Strategien und Bedingungen ohne Parameter; GroupTitForTat ist die Standard-Strategie
        targetCombStrat5 = new CombinedStrategy("SeveralStratsAndConditionsNoParams",
                new BaseStrategy[]{new AlwaysCooperate(), new GrimIndividual(), new GroupTitForTat()},
                new Condition[]{new OwnGroup(), new Richer(), new Always()});
        targetCombStrat6 = new CombinedStrategy("ConditionWithParam",
                new BaseStrategy[]{new NeverCooperate(), new TitForTatIndividual(), new Random()},
                new Condition[]{new Delta(3.14159265359), new SpecificGroup(2), new Always()});



        vmGame1 = new VMGame();
        vmGame1.setName("Gefangenendilemma");
        vmGame1.setDescription("Ich bin eine nichtleere Beschreibung!");
        vmGame1.setPayoffs(new int[][]{new int[] {-1, -1, -3, 0}, new int[] {0, -3, -2, -2}});
        vmGame2 = new VMGame("Vertrauensspiel",
                "",
                new int[][]{new int[] {1, 1, -1, 2}, new int[] {0, 0, 0, 0}});

        parsedGame1 = modelParser.parseVMGame(vmGame1);
        parsedGame2 = modelParser.parseVMGame(vmGame2);

        targetGame1 = new Game("Gefangenendilemma",
                "Ich bin eine nichtleere Beschreibung!",
                new Game.Tuple[][] {new Game.Tuple[] {new Game.Tuple(-1, -1), new Game.Tuple(-3, -0)},
                        new Game.Tuple[] {new Game.Tuple(0, -3), new Game.Tuple(-2, -2)}});

        targetGame2 = new Game("Vertrauensspiel",
                "",
                new Game.Tuple[][] {new Game.Tuple[] {new Game.Tuple(1, 1), new Game.Tuple(-1, 2)},
                        new Game.Tuple[] {new Game.Tuple(0, 0), new Game.Tuple(0, 0)}});
    }

    private static List<HashMap<String, Object>> emptyParamList(int size) {
        List<HashMap<String, Object>> emptyParamList = new ArrayList<>();
        HashMap<String, Object> emptyParamHashMap = new HashMap<>();

        for (int i = 0; i < size; i++) {
            emptyParamList.add(emptyParamHashMap);
        }

        return  emptyParamList;
    }

    @Test
    public void parseSimulationToVMResult() {
    }

    @Test
    public void parseVMCombinedStrategyTest() {

        BaseStrategy[] targetStrats1 = targetCombStrat1.getStrategies();
        BaseStrategy[] targetStrats2 = targetCombStrat2.getStrategies();
        BaseStrategy[] targetStrats3 = targetCombStrat3.getStrategies();
        BaseStrategy[] targetStrats4 = targetCombStrat4.getStrategies();
        BaseStrategy[] targetStrats5 = targetCombStrat5.getStrategies();
        //BaseStrategy[] targetStrats6 = targetCombStrat6.getStrategies();
        BaseStrategy[] parsedStrats1 = parsedCombStrat1.getStrategies();
        BaseStrategy[] parsedStrats2 = parsedCombStrat2.getStrategies();
        BaseStrategy[] parsedStrats3 = parsedCombStrat3.getStrategies();
        BaseStrategy[] parsedStrats4 = parsedCombStrat4.getStrategies();
        BaseStrategy[] parsedStrats5 = parsedCombStrat5.getStrategies();
        //BaseStrategy[] parsedStrats6 = parsedCombStrat6.getStrategies();
        Condition[] targetConditions1 = targetCombStrat1.getConditions();
        Condition[] targetConditions2 = targetCombStrat2.getConditions();
        Condition[] targetConditions3 = targetCombStrat3.getConditions();
        Condition[] targetConditions4 = targetCombStrat4.getConditions();
        Condition[] targetConditions5 = targetCombStrat5.getConditions();
        //Condition[] targetConditions6 = targetCombStrat6.getConditions();
        Condition[] parsedConditions1 = parsedCombStrat1.getConditions();
        Condition[] parsedConditions2 = parsedCombStrat2.getConditions();
        Condition[] parsedConditions3 = parsedCombStrat3.getConditions();
        Condition[] parsedConditions4 = parsedCombStrat4.getConditions();
        Condition[] parsedConditions5 = parsedCombStrat5.getConditions();
        //Condition[] parsedConditions6 = parsedCombStrat6.getConditions();

        //CombinedStrategyTest@calculateActionTest1()
        assertEquals(targetCombStrat1.getName(), parsedCombStrat1.getName());
        assertEquals(targetStrats1.length, parsedStrats1.length);
        assertEquals(targetStrats1[0].getName(), parsedStrats1[0].getName());
        assertEquals(targetConditions1[0].getName(), parsedConditions1[0].getName());
        //CombinedStrategyTest@calculateActionTest2()
        assertEquals(targetCombStrat2.getName(), parsedCombStrat2.getName());
        assertEquals(targetStrats2.length, parsedStrats2.length);
        assertEquals(targetStrats2[0].getName(), parsedStrats2[0].getName());
        assertEquals(targetConditions2[0].getName(), parsedConditions2[0].getName());
        assertEquals(targetStrats2[1].getName(), parsedStrats2[1].getName());
        assertEquals(targetConditions2[1].getName(), parsedConditions2[1].getName());
        //CombinedStrategyTest@calculateActionTest3()
        assertEquals(targetCombStrat3.getName(), parsedCombStrat3.getName());
        assertEquals(targetStrats3.length, parsedStrats3.length);
        assertEquals(targetStrats3[0].getName(), parsedStrats3[0].getName());
        assertEquals(targetConditions3[0].getName(), parsedConditions3[0].getName());
        assertEquals(targetStrats3[1].getName(), parsedStrats3[1].getName());
        assertEquals(targetConditions3[1].getName(), parsedConditions3[1].getName());
        //CombinedStrategyTest@calculateActionTest4()
        assertEquals(targetCombStrat4.getName(), parsedCombStrat4.getName());
        assertEquals(targetStrats4.length, parsedStrats4.length);
        assertEquals(targetStrats4[0].getName(), parsedStrats4[0].getName());
        assertEquals(targetConditions4[0].getName(), parsedConditions4[0].getName());
        //SeveralStratsAndConditionsNoParams
        assertEquals(targetCombStrat5.getName(), parsedCombStrat5.getName());
        assertEquals(targetStrats5.length, parsedStrats5.length);
        assertEquals(targetStrats5[0].getName(), parsedStrats5[0].getName());
        assertEquals(targetConditions5[0].getName(), parsedConditions5[0].getName());
        assertEquals(targetStrats5[1].getName(), parsedStrats5[1].getName());
        assertEquals(targetConditions5[1].getName(), parsedConditions5[1].getName());
        assertEquals(targetStrats5[2].getName(), parsedStrats5[2].getName());
        assertEquals(targetConditions5[2].getName(), parsedConditions5[2].getName());
        //TODO: ConditionWithParam

    }

    @Test
    public void parseVMConfiguration() {
    }

    @Test
    public void parseVMGameTest() {

        //Gefangenendilemma
        assertEquals(targetGame1.getName(), parsedGame1.getName());
        assertEquals(targetGame1.getDescription(), parsedGame1.getDescription());
        assertEquals(targetGame1.getPayoffs(Action.COOPERATION, Action.COOPERATION).getX(),
                parsedGame1.getPayoffs(Action.COOPERATION, Action.COOPERATION).getX());
        assertEquals(targetGame1.getPayoffs(Action.COOPERATION, Action.COOPERATION).getY(),
                parsedGame1.getPayoffs(Action.COOPERATION, Action.COOPERATION).getY());
        assertEquals(targetGame1.getPayoffs(Action.COOPERATION, Action.DEFECTION).getX(),
                parsedGame1.getPayoffs(Action.COOPERATION, Action.DEFECTION).getX());
        assertEquals(targetGame1.getPayoffs(Action.COOPERATION, Action.DEFECTION).getY(),
                parsedGame1.getPayoffs(Action.COOPERATION, Action.DEFECTION).getY());
        assertEquals(targetGame1.getPayoffs(Action.DEFECTION, Action.COOPERATION).getX(),
                parsedGame1.getPayoffs(Action.DEFECTION, Action.COOPERATION).getX());
        assertEquals(targetGame1.getPayoffs(Action.DEFECTION, Action.COOPERATION).getY(),
                parsedGame1.getPayoffs(Action.DEFECTION, Action.COOPERATION).getY());
        assertEquals(targetGame1.getPayoffs(Action.DEFECTION, Action.DEFECTION).getX(),
                parsedGame1.getPayoffs(Action.DEFECTION, Action.DEFECTION).getX());
        assertEquals(targetGame1.getPayoffs(Action.DEFECTION, Action.DEFECTION).getY(),
                parsedGame1.getPayoffs(Action.DEFECTION, Action.DEFECTION).getY());

        //Vertrauensspiel
        assertEquals(targetGame2.getName(), parsedGame2.getName());
        assertEquals(targetGame2.getDescription(), parsedGame2.getDescription());
        assertEquals(targetGame2.getPayoffs(Action.COOPERATION, Action.COOPERATION).getX(),
                parsedGame2.getPayoffs(Action.COOPERATION, Action.COOPERATION).getX());
        assertEquals(targetGame2.getPayoffs(Action.COOPERATION, Action.COOPERATION).getY(),
                parsedGame2.getPayoffs(Action.COOPERATION, Action.COOPERATION).getY());
        assertEquals(targetGame2.getPayoffs(Action.COOPERATION, Action.DEFECTION).getX(),
                parsedGame2.getPayoffs(Action.COOPERATION, Action.DEFECTION).getX());
        assertEquals(targetGame2.getPayoffs(Action.COOPERATION, Action.DEFECTION).getY(),
                parsedGame2.getPayoffs(Action.COOPERATION, Action.DEFECTION).getY());
        assertEquals(targetGame2.getPayoffs(Action.DEFECTION, Action.COOPERATION).getX(),
                parsedGame2.getPayoffs(Action.DEFECTION, Action.COOPERATION).getX());
        assertEquals(targetGame2.getPayoffs(Action.DEFECTION, Action.COOPERATION).getY(),
                parsedGame2.getPayoffs(Action.DEFECTION, Action.COOPERATION).getY());
        assertEquals(targetGame2.getPayoffs(Action.DEFECTION, Action.DEFECTION).getX(),
                parsedGame2.getPayoffs(Action.DEFECTION, Action.DEFECTION).getX());
        assertEquals(targetGame2.getPayoffs(Action.DEFECTION, Action.DEFECTION).getY(),
                parsedGame2.getPayoffs(Action.DEFECTION, Action.DEFECTION).getY());
    }

    @Test
    public void parseVMInitialization() {
    }

    @Test
    public void parseVMStrategy() {
    }
}