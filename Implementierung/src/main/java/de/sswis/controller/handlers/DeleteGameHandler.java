package de.sswis.controller.handlers;

import de.sswis.controller.FileManager;
import de.sswis.view.AbstractManageGamesView;
import de.sswis.view.model.VMGame;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

/**
 * Löscht das ausgewählte {@code Spiel}.
 *
 * @author Max Braun
 */
public class DeleteGameHandler implements ActionListener {

    private AbstractManageGamesView manageGamesView;
    private FileManager fileManager;

    /**
     *
     * @param manageGamesView View, welche das zu löschende {@code Game} beinhaltet
     */
    public DeleteGameHandler(AbstractManageGamesView manageGamesView) {
        this.manageGamesView = manageGamesView;
        this.fileManager = new FileManager();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        VMGame selectedVM = this.manageGamesView.getSelectedVM();
        try {
            this.fileManager.deleteGame(selectedVM.getName());
        } catch (IOException e1) {
            e1.printStackTrace();
            return;
        }
        this.manageGamesView.removeGame(selectedVM.getName());
    }
}
