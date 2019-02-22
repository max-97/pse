package de.sswis.view;

import de.sswis.view.model.VMConfiguration;

import java.awt.event.ActionListener;

/**
 *Ein Fenster zum Verwalten von Konfigurationen.
 *
 * @author Sophie Bräuniger
 */
public interface AbstractManageConfigurationsView extends AbstractView {

    /**
     * Fügt eine Konfiguration hinzu.
     * @param configuration die Benutzereingaben zur Konfiguration
     */
    void addConfiguration(VMConfiguration configuration);

    /**
     * Löscht eine Konfiguration
     * @param configName der Name der Konfiguration
     */
    void removeConfiguration(String configName);


    /**
     * Fügt ein ActionListener zum Button neue Konfiguration hinzu.
     * @param listener ActionListener
     */
    void addNewConfigurationButtonActionlistener(ActionListener listener);

    /**
     * Fügt ein ActionListener zum Button Konfiguration bearbeiten hinzu.
     * @param listener ActionListener
     */
    void addEditConfigurationButtonActionlistener(ActionListener listener);

    /**
     * Fügt ein ActionListener zum Button Konfiguration löschen hinzu.
     * @param listener ActionListener
     */
    void addDeleteConfigurationButtonActionlistener(ActionListener listener);

    /**
     * Fügt ein ActionListener zum Button Änderungen speichern und schließen hinzu.
     * @param listener ActionListener
     */
    void addCloseActionListener(ActionListener listener);

    VMConfiguration getSelectedVM();

    AbstractMainView getParentView();

    void setEditedConfiguration(VMConfiguration vmConfiguration);

    VMConfiguration getEditedConfiguration();

    void replaceConfiguration(VMConfiguration newConfiguration);

}
