package de.sswis.view;

import de.sswis.model.Action;
import de.sswis.view.model.VMResult;

import javax.swing.event.ChangeListener;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.EventListener;
import java.util.List;

/**
 *Ein Fenster zum Vergleichen von Ergebnissen.
 *
 * @author Sophie Bräuniger
 */
public interface AbstractShowCompareView extends AbstractView {


    void addVMResult(VMResult vmResult);

    void addVMResultList(String resultName, ArrayList<VMResult> results);

    AbstractMainView getParentView();
}
