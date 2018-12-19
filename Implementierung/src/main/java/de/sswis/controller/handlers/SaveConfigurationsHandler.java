package de.sswis.controller.handlers;

import de.sswis.view.AbstractNewConfigurationView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Speichert die erstellte {@code Konfiguration}. Die View, die diesen {@code ActionListener} verwendet muss eine
 * {@code Konfiguration} besitzen.
 *
 * @author Max Braun
 */
public class SaveConfigurationsHandler implements ActionListener {

    private AbstractNewConfigurationView configurationView;

    /**
     *
     * @param configurationView die View mit der zu speichernden {@code Konfiguration}
     */
    public SaveConfigurationsHandler(AbstractNewConfigurationView configurationView) {

    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
