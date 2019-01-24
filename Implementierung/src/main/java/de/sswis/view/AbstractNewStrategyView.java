package de.sswis.view;

import de.sswis.view.model.VMStrategy;

import java.awt.event.ActionListener;

/**
 *Ein Fenster zum Erstellen oder Bearbeiten einer gemischten Strategie.
 *
 * @author Sophie Bräuniger
 */
public interface AbstractNewStrategyView extends AbstractView {

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

    VMStrategy getVMStrategy();

    AbstractManageStrategiesView getParentView();

    void setStrategy(VMStrategy strategy);
}
