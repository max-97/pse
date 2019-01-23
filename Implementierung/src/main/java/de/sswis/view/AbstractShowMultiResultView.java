package de.sswis.view;

import java.awt.event.ActionListener;

/**
 *Ein Fenster zum Anzeigen von Ergebnissen einer Mehrfachkonfiguration.
 *
 * @author Sophie Bräuniger
 */
public interface AbstractShowMultiResultView extends AbstractView {

    /**
     * Fügt ein ActionListener zum Button Vergleichen mit hinzu.
     * @param listener ActionListener
     */
    void addCompareButtonActionlistener (ActionListener listener);

    AbstractMainView getParentView();

}
