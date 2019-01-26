package de.sswis.view;

import de.sswis.view.model.VMResult;

import java.awt.event.ActionListener;

/**
 *Ein Fenster zum Anzeigen von Ergebnissen einer Konfiguration.
 *
 * @author Sophie Bräuniger
 */
public interface AbstractShowResultView extends AbstractView {



    AbstractMainView getParentView();

    void setVMResult(VMResult vmResult);
}
