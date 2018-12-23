package de.sswis.view;

import de.sswis.view.model.VMCombinedStrategy;

import java.awt.event.ActionListener;

/**
 *Ein Fenster zum Verwalten von kombinierten Strategien.
 *
 * @author Sophie Bräuniger
 */
public interface AbstractManageCombinedStrategiesView extends AbstractView {


    /**
     * Fügt eine kombinierte Strategie hinzu.
     * @param vmStrategy die Benutzereingaben zur kombinierten Strategie
     */
    void addStrategy(VMCombinedStrategy vmStrategy);

    /**
     * Löscht eine kombinierte Strategie.
     * @param strategyName der Name der kombinierten Strategie
     */
    void removeStrategy(String strategyName);

    /**
     * Fügt ein ActionListener zum Button Simulation starten hinzu.
     * @param listener ActionListener
     */
    void addNewStrategyButtonActionlistener(ActionListener listener);


}
