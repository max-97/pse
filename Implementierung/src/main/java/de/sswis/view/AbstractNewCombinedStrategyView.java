package de.sswis.view;

import de.sswis.view.model.VMCombinedStrategy;

import javax.swing.event.ChangeListener;
import java.awt.event.ActionListener;

/**
 *Ein Fenster zum Erstellen oder Bearbeiten einer kombinierten Strategie.
 *
 * @author Sophie Bräuniger
 */
public interface AbstractNewCombinedStrategyView extends AbstractView {

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

    /**
     * Fügt ein Changelistener zu allen veränderbaren Komponenten,
     * wie Eingabe-Textfelder, Dropdownmenüs und Checkboxes hinzu.
     *
     * @param listener Changelistener
     */
    void addChangeListener(ChangeListener listener);

    VMCombinedStrategy getCombinedStrategy();

    AbstractManageCombinedStrategiesView getParentView();

    void setCombinedStrategy(VMCombinedStrategy combinedStrategy);

    void addCondition(String name);

    void addBaseStrategy(String name);
}
