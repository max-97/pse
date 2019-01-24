package de.sswis.controller.handlers;

import de.sswis.controller.FileManager;
import de.sswis.view.AbstractManageGamesView;
import de.sswis.view.AbstractNewGameView;
import de.sswis.view.model.VMGame;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

/**
 * Speichert das erstellte {@code Spiel}. Die View, die diesen {@code ActionListener} verwendet muss ein
 * {@code Spiel} besitzen.
 *
 * @author Max Braun
 */
public class SaveGamesHandler implements ActionListener {

    private AbstractNewGameView gameView;
    private FileManager fileManager;

    /**
     *
     * @param gameView die View mit dem zu speichernden {@code Spiel}
     */
    public SaveGamesHandler(AbstractNewGameView gameView) {
        this.gameView = gameView;
        this.fileManager = new FileManager();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        VMGame vmGame = this.gameView.getVMGame();
        try {
            this.fileManager.saveGame(vmGame);
        } catch (IOException e1) {
            e1.printStackTrace();
            return;
        }
        AbstractManageGamesView parentView = this.gameView.getParentView();
        if (parentView == null)
            return;
        parentView.addGame(vmGame);
        parentView.update();
        this.gameView.close();
    }
}
