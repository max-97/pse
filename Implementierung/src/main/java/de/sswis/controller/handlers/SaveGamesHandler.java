package de.sswis.controller.handlers;

import de.sswis.view.AbstractNewGameView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Speichert das erstellte {@code Spiel}. Die View, die diesen {@code ActionListener} verwendet muss ein
 * {@code Spiel} besitzen.
 *
 * @author Max Braun
 */
public class SaveGamesHandler implements ActionListener {

    private AbstractNewGameView gameView;

    /**
     *
     * @param gameView die View mit dem zu speichernden {@code Spiel}
     */
    public SaveGamesHandler(AbstractNewGameView gameView) {

    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
