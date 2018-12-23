package de.sswis.view;

import de.sswis.view.model.VMResult;

/**
 *Ein Fenster zum Verwalten von Ergebnissen.
 *
 * @author Sophie Bräuniger
 */
public interface AbstractManageResultsView extends AbstractView {

    /**
     * Fügt ein Ergebnis hinzu.
     * @param vmResult das Ergebnis einer Simulation
     */
    void addResult(VMResult vmResult);

    /**
     * Löscht ein Ergebnis.
     * @param resultName der Name des Ergebnisses
     */
    void removeResult(String resultName);


}
