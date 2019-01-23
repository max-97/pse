package de.sswis.view;

import de.sswis.view.model.VMResult;

import java.awt.event.ActionListener;

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


    /**
     * Fügt ein ActionListener zum Button Ergebnis löschen hinzu.
     * @param listener ActionListener
     */
    void addDeleteResultButtonActionlistener(ActionListener listener);

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

    VMResult getSelectedVM();

    AbstractMainView getParentView();
}
