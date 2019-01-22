package de.sswis.view;

import de.sswis.view.model.VMInitialization;

import java.awt.event.ActionListener;

/**
 *Ein Fenster zum Erstellen oder Bearbeiten einer Initialisierung.
 *
 * @author Sophie Bräuniger
 */
public interface AbstractNewInitializationView extends AbstractView {


    /**
     * Fügt ein ActionListener zum Button Abbrechen hinzu.
     * @param listener ActionListener
     */
    void addCancelButtonActionlistener(ActionListener listener);

    /**
     * Fügt ein ActionListener zum Button Fertig hinzu.
     * @param listener ActionListener
     */
    void addFinishButtonActionlistener(ActionListener listener);

    VMInitialization getVMInitialization();
}
