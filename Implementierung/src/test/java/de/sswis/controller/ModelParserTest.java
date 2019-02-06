package de.sswis.controller;

import de.sswis.model.*;
import de.sswis.view.model.*;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class ModelParserTest {

    private static ModelParser modelParser;

    private static Simulation simulation1;
    private static Simulation simulation2;
    private static VMResult result1;
    private static VMResult result2;

    private static VMCombinedStrategy vmCombStrat1;
    private static VMCombinedStrategy vmCombStrat2;
    private static CombinedStrategy targetCombStrat1;
    private static CombinedStrategy targetCombStrat2;

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

        vmCombStrat1 = new VMCombinedStrategy();
        vmCombStrat1.setName();


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

    @Test
    public void parseSimulationToVMResult() {
    }

    @Test
    public void parseVMCombinedStrategy() {
    }

    @Test
    public void parseVMConfiguration() {
    }

    @Test
    public void parseVMGameMatchesTest() {

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