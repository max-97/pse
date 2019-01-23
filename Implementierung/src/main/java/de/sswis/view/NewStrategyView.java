package de.sswis.view;


import de.sswis.view.model.VMStrategy;

import java.awt.event.ActionListener;

/**
 *Ein Fenster zum Erstellen oder Bearbeiten einer gemischten Strategie.
 *
 * @author Sophie Bräuniger
 */
public class NewStrategyView implements AbstractNewStrategyView {
    private VMStrategy vmStrategy;
    private AbstractManageStrategiesView parentView;

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

    @Override
    public void addCancelButtonActionlistener(ActionListener listener) {

    }

    @Override
    public void addFinishButtonActionlistener(ActionListener listener) {

    }

    @Override
    public VMStrategy getVMStrategy() {
        return null;
    }

    @Override
    public void setParentView(AbstractView parentView) {
        this.parentView = (AbstractManageStrategiesView) parentView;
    }

    @Override
    public AbstractManageStrategiesView getParentView() {
        return this.parentView;
    }
}
