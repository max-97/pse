package de.sswis.controller.handlers;

import de.sswis.controller.FileManager;
import de.sswis.controller.ModelParser;
import de.sswis.controller.ModelProvider;
import de.sswis.model.Configuration;
import de.sswis.view.AbstractManageConfigurationsView;
import de.sswis.view.AbstractNewConfigurationView;
import de.sswis.view.model.VMConfiguration;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.Collection;

/**
 * Speichert die erstellte {@code Konfiguration}. Die View, die diesen {@code ActionListener} verwendet muss eine
 * {@code Konfiguration} besitzen.
 *
 * @author Max Braun
 */
public class SaveConfigurationsHandler implements ActionListener {

    private AbstractNewConfigurationView configurationView;
    private FileManager fileManager;
    private ModelParser parser;

    /**
     *
     * @param configurationView die View mit der zu speichernden {@code Konfiguration}
     */
    public SaveConfigurationsHandler(AbstractNewConfigurationView configurationView) {
        this.configurationView = configurationView;
        this.fileManager = new FileManager();
        this.parser = new ModelParser();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        VMConfiguration vmConfiguration = this.configurationView.getVMConfiguration();
        try {
            this.fileManager.saveConfiguration(vmConfiguration);
        } catch (IOException e1) {
            e1.printStackTrace();
            return;
        }
        Collection<Configuration> configurations = this.parser.parseVMConfiguration(vmConfiguration);
        for (Configuration c : configurations) {
            ModelProvider.getInstance().addConfiguration(c);
        }
        AbstractManageConfigurationsView parentView = this.configurationView.getParentView();
        if (parentView == null)
            return;
        parentView.addConfiguration(vmConfiguration);
        parentView.update();
        this.configurationView.close();
    }
}
