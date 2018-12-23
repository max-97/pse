package de.sswis.view;

import de.sswis.view.model.VMInitialization;

/**
 *Ein Fenster zum Verwalten von Initialisierungen.
 *
 * @author Sophie Bräuniger
 */
public interface AbstractManageInitializationsView {

    /**
     * Fügt eine Initialisierung hinzu.
     * @param vmInitialization die Benutzereingaben zur Initialisierung
     */
    void addInit(VMInitialization vmInitialization);

    /**
     * Löscht eine Initialisierung.
     * @param initName der Name der Initialisierung
     */
    void removeInit(String initName);

    /**
     *Aktualisiert alle Komponenten.
     */
    void update();
}
