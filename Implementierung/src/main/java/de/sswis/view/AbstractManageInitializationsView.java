package de.sswis.view;

import de.sswis.view.model.VMInitialization;

import java.awt.event.ActionListener;

/**
 *Ein Fenster zum Verwalten von Initialisierungen.
 *
 * @author Sophie Bräuniger
 */
public interface AbstractManageInitializationsView extends AbstractView {

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
     * Fügt ein ActionListener zum Button neue Initialisierung hinzu.
     * @param listener ActionListener
     */
    void addNewInitButtonActionlistener(ActionListener listener);

    /**
     * Fügt ein ActionListener zum Button Initialisierung bearbeiten hinzu.
     * @param listener ActionListener
     */
    void addEditInitButtonActionlistener(ActionListener listener);

    /**
     * Fügt ein ActionListener zum Button Initialisierung löschen hinzu.
     * @param listener ActionListener
     */
    void addDeleteInitButtonActionlistener(ActionListener listener);

    /**
     * Fügt ein ActionListener zum Button Abbrechen hinzu.
     * @param listener ActionListener
     */
    void addCancelButtonActionlistener(ActionListener listener);

    /**
     * Fügt ein ActionListener zum Button Änderungen speichern und schließen hinzu.
     * @param listener ActionListener
     */
    void addSaveQuitButtonActionlistener(ActionListener listener);

    VMInitialization getSelectedVM();
}
