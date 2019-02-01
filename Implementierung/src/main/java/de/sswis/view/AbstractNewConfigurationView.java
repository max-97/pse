package de.sswis.view;

import de.sswis.view.model.VMConfiguration;

import javax.swing.event.ChangeListener;
import java.awt.event.ActionListener;
import java.util.EventListener;
import java.util.HashMap;

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

    AbstractManageConfigurationsView getParentView();

    void setConfiguration(VMConfiguration configuration);

    void addAdaptionAlgorithm(String name);

    void addPairingAlgorithm(String name);

    void addRankingAlgorithm(String name);

    void addInitialization(String name);

    void addGame(String name);

    void addParameters(HashMap<String, String[]> parameters);
}
