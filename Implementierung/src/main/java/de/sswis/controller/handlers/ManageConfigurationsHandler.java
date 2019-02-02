package de.sswis.controller.handlers;

import de.sswis.controller.FileManager;
import de.sswis.view.AbstractGuiFactory;
import de.sswis.view.AbstractMainView;
import de.sswis.view.AbstractManageConfigurationsView;
import de.sswis.view.model.VMConfiguration;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Öffnet die View zum Verwalten der {@code Konfigurationen}.
 *
 * @author Max Braun
 */
public class ManageConfigurationsHandler implements ActionListener {

    private AbstractGuiFactory factory;
    private AbstractMainView mainView;
    private FileManager fileManager;

    /**
     *
     * @param factory Fabrik zum Erstellen der View
     */
    public ManageConfigurationsHandler(AbstractGuiFactory factory, AbstractMainView view) {
        this.factory = factory;
        this.mainView = view;
        fileManager = new FileManager();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        AbstractManageConfigurationsView manageConfigurationsView = this.factory.createManageConfigurationsView();
        manageConfigurationsView.setParentView(mainView);
        for(VMConfiguration c : this.fileManager.loadAllConfigurations()) {
            manageConfigurationsView.addConfiguration(c);
        }
        manageConfigurationsView.show();
    }
}
