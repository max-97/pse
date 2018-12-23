package de.sswis.view;

import de.sswis.view.model.VMStrategy;

import java.awt.event.ActionListener;

/**
 *Ein Fenster zum Verwalten von gemischten Strategien.
 *
 * @author Sophie Bräuniger
 */
public interface AbstractManageStrategiesView extends AbstractView {

    /**
     * Fügt eine gemischte Strategie hinzu.
     * @param vmStrategy die Benutzereingaben zur gemischten Strategie
     */
    void addResult(VMStrategy vmStrategy);

    /**
     * Löscht eine gemischte Strategie.
     * @param strategyName der Name der gemischten Strategie
     */
    void removeResult(String strategyName);


    /**
     * Fügt ein ActionListener zum Button neue Strategie hinzu.
     * @param listener ActionListener
     */
    void addNewStrategyButtonActionlistener(ActionListener listener);

    /**
     * Fügt ein ActionListener zum Button Strategie bearbeiten hinzu.
     * @param listener ActionListener
     */
    void addEditStrategyButtonActionlistener(ActionListener listener);

    /**
     * Fügt ein ActionListener zum Button Strategie löschen hinzu.
     * @param listener ActionListener
     */
    void addDeleteStrategyButtonActionlistener(ActionListener listener);

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


}
