package de.sswis.controller.handlers;

import de.sswis.view.AbstractGuiFactory;
import de.sswis.view.AbstractManageStrategiesView;
import de.sswis.view.AbstractNewStrategyView;
import de.sswis.view.model.VMStrategy;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Öffnet die View zum Bearbeiten einer {@code gemischten Strategie}.
 *
 * @author Max Braun
 */
public class EditStrategyHandler implements ActionListener {

    private AbstractGuiFactory factory;
    private AbstractManageStrategiesView manageStrategiesView;

    /**
     *
     * @param factory Fabrik zum Erstellen der View
     */
    public EditStrategyHandler(AbstractGuiFactory factory, AbstractManageStrategiesView view) {
        this.factory = factory;
        this.manageStrategiesView = view;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        AbstractNewStrategyView newStrategyView = this.factory.createNewStrategyView();
        newStrategyView.setParentView(this.manageStrategiesView);
        VMStrategy selectedVM = this.manageStrategiesView.getSelectedVM();
        newStrategyView.setStrategy(selectedVM);
        newStrategyView.update();
        newStrategyView.show();
    }
}
