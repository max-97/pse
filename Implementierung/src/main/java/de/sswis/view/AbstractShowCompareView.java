package de.sswis.view;

import de.sswis.model.Action;
import de.sswis.view.model.VMResult;

import java.awt.event.ActionListener;
import java.util.List;

/**
 *Ein Fenster zum Vergleichen von Ergebnissen.
 *
 * @author Sophie Bräuniger
 */
public interface AbstractShowCompareView extends AbstractView {


    /**
     * Fügt ein ActionListener zum Button Vergleichen mit hinzu.
     * @param listener ActionListener
     */
    void addCompareButtonActionlistener (ActionListener listener);

    AbstractMainView getParentView();
}
