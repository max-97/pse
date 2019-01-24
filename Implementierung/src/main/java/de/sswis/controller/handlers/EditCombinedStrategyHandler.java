package de.sswis.controller.handlers;

import de.sswis.view.AbstractGuiFactory;
import de.sswis.view.AbstractManageCombinedStrategiesView;
import de.sswis.view.AbstractNewCombinedStrategyView;
import de.sswis.view.model.VMCombinedStrategy;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Öffnet die View zum Bearbeiten einer {@code kombinierten Strategie}.
 *
 * @author Max Braun
 */
public class EditCombinedStrategyHandler implements ActionListener {

    private AbstractGuiFactory factory;
    private AbstractManageCombinedStrategiesView manageCombinedStrategiesView;

    /**
     *
     * @param factory Fabrik zum Erstellen der View
     */
    public EditCombinedStrategyHandler(AbstractGuiFactory factory, AbstractManageCombinedStrategiesView view) {
        this.factory = factory;
        this.manageCombinedStrategiesView = view;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        AbstractNewCombinedStrategyView newCombinedStrategyView = this.factory.createNewCombinedStrategyView();
        newCombinedStrategyView.setParentView(manageCombinedStrategiesView);
        VMCombinedStrategy selectedVM = this.manageCombinedStrategiesView.getSelectedVM();
        newCombinedStrategyView.setCombinedStrategy(selectedVM);
        newCombinedStrategyView.update();
        newCombinedStrategyView.show();
    }
}
