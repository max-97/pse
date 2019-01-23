package de.sswis.view;

import de.sswis.view.AbstractManageResultsView;
import de.sswis.view.model.VMResult;

import java.awt.event.ActionListener;

/**
 *Ein Fenster zum Verwalten von Ergebnissen.
 *
 * @author Sophie Bräuniger
 */
public class ManageResultsView implements AbstractManageResultsView {

    private AbstractMainView parentView;

    @Override
    public void addResult(VMResult vmResult) {

    }

    @Override
    public void removeResult(String resultName) {

    }

    @Override
    public void addDeleteResultButtonActionlistener(ActionListener listener) {

    }

    @Override
    public void addCancelButtonActionlistener(ActionListener listener) {

    }

    @Override
    public void addSaveQuitButtonActionlistener(ActionListener listener) {

    }

    @Override
    public VMResult getSelectedVM() {
        return null;
    }

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
    public void setParentView(AbstractView parentView) {
        this.parentView = (AbstractMainView) parentView;
    }

    @Override
    public AbstractMainView getParentView() {
        return this.parentView;
    }
}
