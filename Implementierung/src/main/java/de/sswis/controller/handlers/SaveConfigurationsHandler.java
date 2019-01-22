package de.sswis.controller.handlers;

import de.sswis.controller.FileManager;
import de.sswis.view.AbstractManageConfigurationsView;
import de.sswis.view.AbstractNewConfigurationView;
import de.sswis.view.model.VMConfiguration;

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
    private FileManager fileManager;

    /**
     *
     * @param configurationView die View mit der zu speichernden {@code Konfiguration}
     */
    public SaveConfigurationsHandler(AbstractNewConfigurationView configurationView) {
        this.configurationView = configurationView;
        this.fileManager = new FileManager();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        VMConfiguration vmConfiguration = this.configurationView.getVMConfiguration();
        this.fileManager.saveConfiguration(vmConfiguration);
        AbstractManageConfigurationsView parenteView = this.configurationView.getParenteView();
        parenteView.addConfiguration(vmConfiguration);
        parenteView.update();
        this.configurationView.close();
    }
}
