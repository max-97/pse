package de.sswis.controller.handlers;

import de.sswis.view.AbstractGuiFactory;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Öffnet eine View zum Erstellen einer {@code Konfiguration}.
 *
 * @author Max Braun
 */
public class NewConfigurationViewHandler implements ActionListener {

    private AbstractGuiFactory factory;

    /**
     *
     * @param factory Fabrik zum Erstellen der View
     */
    public NewConfigurationViewHandler(AbstractGuiFactory factory) {

    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
