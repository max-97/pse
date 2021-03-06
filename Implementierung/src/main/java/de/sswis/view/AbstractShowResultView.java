package de.sswis.view;

import de.sswis.view.model.VMResult;

import javax.swing.event.ChangeListener;
import java.awt.event.ActionListener;
import java.util.EventListener;
import java.util.List;

/**
 *Ein Fenster zum Anzeigen von Ergebnissen einer Konfiguration.
 *
 * @author Sophie Bräuniger
 */
public interface AbstractShowResultView extends AbstractView {


    /**
     * Fügt ein ActionListener zum Button Vergleichen mit hinzu.
     * @param listener ActionListener
     */
    void addCompareButtonActionlistener (ActionListener listener);

    AbstractMainView getParentView();

    void addVMResult(VMResult vmResult);

    List<VMResult> getResults();
}
