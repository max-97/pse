package de.sswis.controller.handlers;

import de.sswis.view.AbstractGuiFactory;
import de.sswis.view.AbstractManageGamesView;
import de.sswis.view.AbstractNewGameView;
import de.sswis.view.model.VMGame;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Öffnet die View zum Bearbeiten eines {@code Spiels}.
 *
 * @author Max Braun
 */
public class EditGameHandler implements ActionListener {

    private AbstractGuiFactory factory;
    private AbstractManageGamesView manageGamesView;

    /**
     *
     * @param factory Fabrik zum Erstellen der View
     */
    public EditGameHandler(AbstractGuiFactory factory, AbstractManageGamesView view) {
        this.factory = factory;
        this.manageGamesView = view;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        AbstractNewGameView newGameView = this.factory.createNewGameView();
        newGameView.setParentView(manageGamesView);
        VMGame selectedVM = this.manageGamesView.getSelectedVM();
        newGameView.setGame(selectedVM);
        newGameView.show();
    }
}
