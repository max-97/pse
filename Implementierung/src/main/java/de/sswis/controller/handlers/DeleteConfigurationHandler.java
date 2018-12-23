package de.sswis.controller.handlers;

import de.sswis.view.AbstractManageConfigurationsView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Löscht die ausgewählte {@code Konfiguration}.
 *
 * @author Max Braun
 */
public class DeleteConfigurationHandler implements ActionListener {

    private AbstractManageConfigurationsView manageConfigurationsView;

    /**
     *
     * @param manageConfigurationsView View, welche die zu löschende {@code Configuration} beinhaltet
     */
    public DeleteConfigurationHandler(AbstractManageConfigurationsView manageConfigurationsView) {

    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
