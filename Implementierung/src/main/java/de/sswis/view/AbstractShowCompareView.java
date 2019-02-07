package de.sswis.view;

import de.sswis.model.Action;
import de.sswis.view.model.VMResult;

import javax.swing.event.ChangeListener;
import java.awt.event.ActionListener;
import java.util.EventListener;
import java.util.List;

/**
 *Ein Fenster zum Vergleichen von Ergebnissen.
 *
 * @author Sophie Bräuniger
 */
public interface AbstractShowCompareView extends AbstractView {


    void addVMResult(VMResult vmResult);

    AbstractMainView getParentView();
}
