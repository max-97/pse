package de.sswis.controller.handlers;

import de.sswis.controller.FileManager;
import de.sswis.view.AbstractManageConfigurationsView;
import de.sswis.view.model.VMConfiguration;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Löscht die ausgewählte {@code Konfiguration}.
 *
 * @author Max Braun
 */
public class DeleteConfigurationHandler implements ActionListener {

    private AbstractManageConfigurationsView manageConfigurationsView;
    private FileManager fileManager;

    /**
     *
     * @param manageConfigurationsView View, welche die zu löschende {@code Configuration} beinhaltet
     */
    public DeleteConfigurationHandler(AbstractManageConfigurationsView manageConfigurationsView) {
        this.manageConfigurationsView = manageConfigurationsView;
        this.fileManager = new FileManager();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        VMConfiguration selectedVM = this.manageConfigurationsView.getSelectedVM();
        this.manageConfigurationsView.removeConfiguration(selectedVM.getName());
        this.manageConfigurationsView.update();
        this.fileManager.deleteConfiguration(selectedVM.getName());
    }
}
