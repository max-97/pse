package de.sswis.controller.handlers;

import de.sswis.view.AbstractGuiFactory;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Erstellt eine neuen Konfiguration. In der View zum Verwalten der {@code Konfigurationen} wird eine neue
 * {@code Konfiguration} hinzugefügt und es öffnet sich die View zum Bearbeiten der neuen {@code Konfiguration}.
 *
 * @author Max Braun
 */
public class NewConfigurationHandler implements ActionListener {

    private AbstractGuiFactory factory;

    /**
     *
     * @param factory Fabrik zum Erstellen der View
     */
    public NewConfigurationHandler(AbstractGuiFactory factory) {

    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
