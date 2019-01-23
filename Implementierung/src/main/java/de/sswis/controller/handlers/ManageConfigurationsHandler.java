package de.sswis.controller.handlers;

import de.sswis.view.AbstractGuiFactory;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Öffnet die View zum Verwalten der {@code Konfigurationen}.
 *
 * @author Max Braun
 */
public class ManageConfigurationsHandler implements ActionListener {

    private AbstractGuiFactory factory;

    /**
     *
     * @param factory Fabrik zum Erstellen der View
     */
    public ManageConfigurationsHandler(AbstractGuiFactory factory) {

    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
