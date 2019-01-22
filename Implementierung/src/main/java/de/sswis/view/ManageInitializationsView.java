package de.sswis.view;

import de.sswis.view.AbstractManageInitializationsView;
import de.sswis.view.model.VMInitialization;

import java.awt.event.ActionListener;
import java.util.List;

/**
 *Ein Fenster zum Verwalten von Initialisierungen.
 *
 * @author Sophie Bräuniger
 */
public class ManageInitializationsView implements AbstractManageInitializationsView {

    private List<VMInitialization> vmInits;

    @Override
    public void addInit(VMInitialization vmInitialization) {

    }

    @Override
    public void removeInit(String initName) {

    }

    @Override
    public void addNewInitButtonActionlistener(ActionListener listener) {

    }

    @Override
    public void addEditInitButtonActionlistener(ActionListener listener) {

    }

    @Override
    public void addDeleteInitButtonActionlistener(ActionListener listener) {

    }

    @Override
    public void addCancelButtonActionlistener(ActionListener listener) {

    }

    @Override
    public void addSaveQuitButtonActionlistener(ActionListener listener) {

    }

    @Override
    public VMInitialization getSelectedVM() {
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

    }


}
