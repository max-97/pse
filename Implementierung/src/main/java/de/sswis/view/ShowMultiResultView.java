package de.sswis.view;

import de.sswis.view.model.VMConfiguration;
import de.sswis.view.model.VMResult;

import java.awt.event.ActionListener;
import java.util.List;

/**
 *Ein Fenster zum Anzeigen von Ergebnissen einer Mehrfachkonfiguration.
 *
 * @author Sophie Bräuniger
 */
public class ShowMultiResultView implements AbstractShowMultiResultView{
    private List<VMConfiguration> vmConfigurations;
    private AbstractMainView parentView;

    @Override
    public void update() {

    }

    @Override
    public void show() {

    }

    @Override
    public void close() {

    }

    @Override
    public void addCompareButtonActionlistener(ActionListener listener) {

    }

    @Override
    public void setParentView(AbstractView parentView) {
        this.parentView = (AbstractMainView) parentView;
    }

    @Override
    public AbstractMainView getParentView() {
        return this.parentView;
    }

    @Override
    public void addVMResult(VMResult vmResult) {

    }
}
