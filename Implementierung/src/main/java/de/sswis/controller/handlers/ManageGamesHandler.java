package de.sswis.controller.handlers;

import de.sswis.controller.FileManager;
import de.sswis.view.AbstractGuiFactory;
import de.sswis.view.AbstractMainView;
import de.sswis.view.AbstractManageGamesView;
import de.sswis.view.model.VMGame;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Öffnet die View zum Verwalten der {@code Spiele}.
 *
 * @author Max Braun
 */
public class ManageGamesHandler implements ActionListener {

    private AbstractGuiFactory factory;
    private AbstractMainView mainView;
    private FileManager fileManager;

    /**
     *
     * @param factory Fabrik zum Erstellen der View
     */
    public ManageGamesHandler(AbstractGuiFactory factory, AbstractMainView view) {
        this.factory = factory;
        this.mainView = view;
        this.fileManager = new FileManager();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        AbstractManageGamesView manageGamesView = this.factory.createManageGamesView();
        manageGamesView.setParentView(mainView);
        for(VMGame g : this.fileManager.loadAllGames()) {
            manageGamesView.addGame(g);
        }
        manageGamesView.update();
        manageGamesView.show();
    }
}
