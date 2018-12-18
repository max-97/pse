package de.sswis.controller.handlers;

import de.sswis.view.Konfiguration;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Speichert die erstellte {@code Konfiguration}. Die View, die diesen {@code ActionListener} verwendet muss eine
 * {@code Konfiguration} besitzen.
 *
 * @author Max Braun
 */
public class SaveConfigurationsHandler implements ActionListener {

    private Konfiguration configurationView;

    /**
     *
     * @param configurationView die View mit der zu speichernden {@code Konfiguration}
     */
    public SaveConfigurationsHandler(Konfiguration configurationView) {

    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
