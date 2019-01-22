package de.sswis.view;

import de.sswis.view.model.VMConfiguration;

import java.awt.event.ActionListener;

/**
 *Ein Fenster zum Erstellen oder Bearbeiten einer Konfiguration.
 *
 * @author Sophie Bräuniger
 */
public interface AbstractNewConfigurationView extends AbstractView {


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

    VMConfiguration getVMConfiguration();

    AbstractManageConfigurationsView getParenteView();

}
