package de.sswis.controller.handlers;

import de.sswis.view.AbstractGuiFactory;
import de.sswis.view.AbstractNewGameView;
import de.sswis.view.model.VMGame;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Öffnet eine View zum Erstellen eines {@code Spiels}.
 *
 * @author Max Braun
 */
public class NewGameViewHandler implements ActionListener {

    private AbstractGuiFactory factory;

    /**
     *
     * @param factory Fabrik zum Erstellen der View
     */
    public NewGameViewHandler(AbstractGuiFactory factory) {
        this.factory = factory;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        AbstractNewGameView newGameView = this.factory.createNewGameView();
        newGameView.setParentView(null);
        newGameView.setGame(new VMGame());
        newGameView.show();
    }
}
