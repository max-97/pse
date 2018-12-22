package de.sswis.controller.handlers;

import de.sswis.controller.AbstractGuiFactory;

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

    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
