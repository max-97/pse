package de.sswis.controller.handlers;

import de.sswis.view.AbstractManageGamesView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Löscht das ausgewählte {@code Spiel}.
 *
 * @author Max Braun
 */
public class DeleteGameHandler implements ActionListener {

    private AbstractManageGamesView manageGamesView;

    /**
     *
     * @param manageGamesView View, welche das zu löschende {@code Game} beinhaltet
     */
    public DeleteGameHandler(AbstractManageGamesView manageGamesView) {

    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
