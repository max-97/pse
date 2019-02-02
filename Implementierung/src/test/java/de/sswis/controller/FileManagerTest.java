package de.sswis.controller;

import de.sswis.view.model.VMGame;
import org.junit.Test;

import java.io.IOException;

public class FileManagerTest {

    @Test
    public void savedInCorrectDir() throws IOException {
        /*
        VMGame game = new VMGame();
        game.setName("game");
        game.setDescription("Test");

        FileManager fm = new FileManager();
        fm.saveGame(game);
        */
    }

    @Test
    public void deleteGame() throws IOException {
        FileManager fm = new FileManager();
        fm.deleteGame("game");
    }
}
