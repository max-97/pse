package de.sswis.view;

import de.sswis.view.model.VMStrategy;

/**
 *Ein Fenster zum Verwalten von gemischten Strategien.
 *
 * @author Sophie Bräuniger
 */
public interface AbstractManageStrategiesView {

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
     *Aktualisiert alle Komponenten.
     */
    void update();
}
