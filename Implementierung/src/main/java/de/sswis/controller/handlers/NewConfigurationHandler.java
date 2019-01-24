package de.sswis.controller.handlers;

import de.sswis.view.AbstractGuiFactory;
import de.sswis.view.AbstractManageConfigurationsView;
import de.sswis.view.AbstractNewConfigurationView;
import de.sswis.view.model.VMConfiguration;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Erstellt eine neuen Konfiguration. In der View zum Verwalten der {@code Konfigurationen} wird eine neue
 * {@code Konfiguration} hinzugefügt und es öffnet sich die View zum Bearbeiten der neuen {@code Konfiguration}.
 *
 * @author Max Braun
 */
public class NewConfigurationHandler implements ActionListener {

    private AbstractGuiFactory factory;
    private AbstractManageConfigurationsView configurationsView;

    /**
     *
     * @param factory Fabrik zum Erstellen der View
     */
    public NewConfigurationHandler(AbstractGuiFactory factory, AbstractManageConfigurationsView view) {
        this.factory = factory;
        this.configurationsView = view;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        AbstractNewConfigurationView newConfigurationView = this.factory.createNewConfigurationView();
        newConfigurationView.setParentView(configurationsView);
        newConfigurationView.setConfiguration(new VMConfiguration());
        newConfigurationView.update();
        newConfigurationView.show();
    }
}
