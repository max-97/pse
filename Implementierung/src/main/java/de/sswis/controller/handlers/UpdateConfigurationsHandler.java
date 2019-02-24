package de.sswis.controller.handlers;

import de.sswis.controller.FileManager;
import de.sswis.view.AbstractMainView;
import de.sswis.view.model.VMConfiguration;

import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.util.Collection;

public class UpdateConfigurationsHandler implements FocusListener {

    private AbstractMainView mainView;
    private FileManager fileManager;

    public UpdateConfigurationsHandler(AbstractMainView view) {
        this.mainView = view;
        this.fileManager = new FileManager();
    }

    @Override
    public void focusGained(FocusEvent e) {
        Collection<VMConfiguration> vmConfigurations = this.fileManager.loadAllConfigurations();
        this.mainView.removeAll();
        for (VMConfiguration c : vmConfigurations) {
            this.mainView.addConfiguration(c);
        }
    }

    @Override
    public void focusLost(FocusEvent e) {
        // Do nothing
    }

}
