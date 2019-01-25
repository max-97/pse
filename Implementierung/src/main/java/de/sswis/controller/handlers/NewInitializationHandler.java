package de.sswis.controller.handlers;

import de.sswis.view.AbstractGuiFactory;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Erstellt eine neue Initialisierung. In der View zum Verwalten der {@code Initialisierungen} wird eine neue
 * {@code Initialisierung} hinzugefügt und es öffnet sich die View zum Bearbeiten der neuen {@code Initialisierung}.
 *
 * @author Max Braun
 */
public class NewInitializationHandler implements ActionListener {

    private AbstractGuiFactory factory;

    /**
     *
     * @param factory Fabrik zum Erstellen der View
     */
    public NewInitializationHandler(AbstractGuiFactory factory) {
        this.factory = factory;
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
