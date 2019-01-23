package de.sswis.view;

import de.sswis.view.AbstractNewCombinedStrategyView;
import de.sswis.view.model.VMCombinedStrategy;

import java.awt.event.ActionListener;

/**
 *Ein Fenster zum Erstellen oder Bearbeiten einer kombinierten Strategie.
 *
 * @author Sophie Bräuniger
 */
public class NewCombinedStrategyView implements AbstractNewCombinedStrategyView {
    private VMCombinedStrategy vmCombinedStrategy;
    private AbstractManageCombinedStrategiesView parentView;


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
    public VMCombinedStrategy getCombinedStrategy() {
        return null;
    }

    @Override
    public void setParentView(AbstractView parentView) {
        this.parentView = (AbstractManageCombinedStrategiesView) parentView;
    }

    @Override
    public AbstractManageCombinedStrategiesView getParentView() {
        return this.parentView;
    }
}
