package de.sswis.controller.handlers;

import de.sswis.view.AbstractGuiFactory;
import de.sswis.view.AbstractManageGamesView;
import de.sswis.view.AbstractNewGameView;
import de.sswis.view.model.VMGame;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Erstellt ein neues Spiel. In der View zum Verwalten der {@code Spiele} wird ein neues
 * {@code Spiel} hinzugefügt und es öffnet sich die View zum Bearbeiten der neuen {@code Spiele}.
 *
 * @author Max Braun
 */
public class NewGameHandler implements ActionListener {

    private AbstractGuiFactory factory;
    private AbstractManageGamesView gamesView;

    /**
     *
     * @param factory Fabrik zum Erstellen der View
     */
    public NewGameHandler(AbstractGuiFactory factory, AbstractManageGamesView view) {
        this.factory = factory;
        this.gamesView = view;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        AbstractNewGameView newGameView = this.factory.createNewGameView();
        newGameView.setParentView(gamesView);
        newGameView.setGame(new VMGame());
        newGameView.update();
        newGameView.show();
    }
}
