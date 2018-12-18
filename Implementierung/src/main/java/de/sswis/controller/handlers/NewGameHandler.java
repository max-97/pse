package de.sswis.controller.handlers;

import de.sswis.controller.AbstractGuiFactory;

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

    /**
     *
     * @param factory Fabrik zum Erstellen der View
     */
    public NewGameHandler(AbstractGuiFactory factory) {
        this.factory = factory;
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
