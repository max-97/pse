package de.sswis.controller;

import de.sswis.exceptions.DuplicateObjectNameException;
import de.sswis.model.*;
import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class ModelProviderTest {

    private static ModelProvider modelProvider;

    private static Game game;
    private static Configuration configuration;
    private static Initialization initialization;
    private static MixedStrategy strategy;
    private static CombinedStrategy combinedStrategy;

    @BeforeClass
    public static void instantiate() {
        modelProvider = ModelProvider.getInstance();
        game = new Game("testGame", "", null);
        configuration = new Configuration("testConfig", null, null, null,
                null, null, 0, 0, 0);
        initialization = new Initialization("testInit", 0);
        strategy = new MixedStrategy("testStrategy", null, null);
        combinedStrategy = new CombinedStrategy("testCombStrat", null, null);
    }

    @Before
    public void init() {
        modelProvider.addGame(game);
        modelProvider.addConfiguration(configuration);
        modelProvider.addInitialization(initialization);
        modelProvider.addMixedStrategy(strategy);
        modelProvider.addCombinedStrategy(combinedStrategy);
    }

    @After
    public void tearDown() {
        modelProvider.deleteAllObjects();
    }

    @Test
    public void objectsShouldBeAdded() {
        assertEquals(game, modelProvider.getGame("testGame"));
        assertEquals(configuration, modelProvider.getConfiguration("testConfig"));
        assertEquals(initialization, modelProvider.getInitialization("testInit"));
        assertEquals(strategy, modelProvider.getMixedStrategy("testStrategy"));
        assertEquals(combinedStrategy, modelProvider.getCombinedStrategy("testCombStrat"));
    }

    @Test
    public void objectsShouldBeDeleted() {
        modelProvider.deleteGame("testGame");
        modelProvider.deleteConfiguration("testConfig");
        modelProvider.deleteInitialization("testInit");
        modelProvider.deleteMixedStrategy("testStrategy");
        modelProvider.deleteCombinedStrategy("testCombStrat");

        assertNull(modelProvider.getGame("testGame"));
        assertNull(modelProvider.getConfiguration("testConfig"));
        assertNull(modelProvider.getInitialization("testInit"));
        assertNull(modelProvider.getMixedStrategy("testStrategy"));
        assertNull(modelProvider.getCombinedStrategy("testCombStrat"));
    }

    @Test
    public void nothingShouldBeDeleted() {
        modelProvider.deleteGame("foo");
        modelProvider.deleteConfiguration("foo");
        modelProvider.deleteInitialization("testinit");
        modelProvider.deleteMixedStrategy("Strategy");
        modelProvider.deleteCombinedStrategy("test");

        assertEquals(game, modelProvider.getGame("testGame"));
        assertEquals(configuration, modelProvider.getConfiguration("testConfig"));
        assertEquals(initialization, modelProvider.getInitialization("testInit"));
        assertEquals(strategy, modelProvider.getMixedStrategy("testStrategy"));
        assertEquals(combinedStrategy, modelProvider.getCombinedStrategy("testCombStrat"));
    }

    @Test(expected = DuplicateObjectNameException.class)
    public void shouldRaiseDuplicateNameExceptionGame() {
        Game otherGame = new Game("testGame", "", null);
        modelProvider.addGame(otherGame);
    }

    @Test(expected = DuplicateObjectNameException.class)
    public void shouldRaiseDuplicateNameExceptionConfig() {
        /*
        Configuration otherConfiguration = new Configuration("testConfig", null, null, null,
                null, 0, 0, 0, null);
        modelProvider.addConfiguration(otherConfiguration);
        */
    }

    @Test(expected = DuplicateObjectNameException.class)
    public void shouldRaiseDuplicateNameExceptionInit() {
        Initialization otherInitialization = new Initialization("testInit", 0);
        modelProvider.addInitialization(otherInitialization);
    }

    @Test(expected = DuplicateObjectNameException.class)
    public void shouldRaiseDuplicateNameExceptionStrategy() {
        /*
        Strategy otherStrategy = new Strategy("testStrategy", null, null);
        modelProvider.addMixedStrategy(otherStrategy);
        */
    }

    @Test(expected = DuplicateObjectNameException.class)
    public void shouldRaiseDuplicateNameExceptionCombStrategy() {
        CombinedStrategy otherCombStrat = new CombinedStrategy("testCombStrat", null, null);
        modelProvider.addCombinedStrategy(otherCombStrat);
    }

}
