package de.sswis.controller.handlers;

import de.sswis.view.AbstractGuiFactory;
import de.sswis.view.AbstractManageConfigurationsView;
import de.sswis.view.AbstractNewConfigurationView;
import de.sswis.view.model.VMConfiguration;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Öffnet die View zum Bearbeiten einer {@code Konfiguration}.
 *
 * @author Max Braun
 */
public class EditConfigurationHandler implements ActionListener {

    private AbstractGuiFactory factory;
    private AbstractManageConfigurationsView manageConfigurationsView;

    /**
     *
     * @param factory Fabrik zum Erstellen der View
     */
    public EditConfigurationHandler(AbstractGuiFactory factory, AbstractManageConfigurationsView view) {
        this.factory = factory;
        this.manageConfigurationsView = view;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        AbstractNewConfigurationView newConfigurationView = this.factory.createNewConfigurationView();
        newConfigurationView.setParentView(this.manageConfigurationsView);
        VMConfiguration selectedVM = this.manageConfigurationsView.getSelectedVM();
        newConfigurationView.setConfiguration(selectedVM);
        newConfigurationView.update();
        newConfigurationView.show();
    }
}
