package de.sswis.view;

import de.sswis.view.model.VMConfiguration;

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


}
