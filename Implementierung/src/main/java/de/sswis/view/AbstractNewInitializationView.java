package de.sswis.view;

import de.sswis.view.model.VMInitialization;

import javax.swing.event.ChangeListener;
import java.awt.event.ActionListener;
import java.util.EventListener;

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

    AbstractManageInitializationsView getParentView();

    void setInitialization(VMInitialization initialization);

    void addStrategy(String name);
}
