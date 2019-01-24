package de.sswis.controller.handlers;

import de.sswis.view.AbstractGuiFactory;
import de.sswis.view.AbstractManageCombinedStrategiesView;
import de.sswis.view.AbstractNewCombinedStrategyView;
import de.sswis.view.model.VMCombinedStrategy;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Erstellt eine neue kombinierte Strategie. In der View zum Verwalten der {@code kombinierten Strategien} wird eine
 * neue {@code kombinierte Strategie} hinzugefügt und es öffnet sich die View zum Bearbeiten der neuen
 * {@code kombinierte Strategie}.
 *
 * @author Max Braun
 */
public class NewCombinedStrategyHandler implements ActionListener {

    private AbstractGuiFactory factory;
    private AbstractManageCombinedStrategiesView combinedStrategiesView;

    /**
     *
     * @param factory Fabrik zum Erstellen der View
     */
    public NewCombinedStrategyHandler(AbstractGuiFactory factory, AbstractManageCombinedStrategiesView view) {
        this.factory = factory;
        this.combinedStrategiesView = view;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        AbstractNewCombinedStrategyView newCombinedStrategyView = this.factory.createNewCombinedStrategyView();
        newCombinedStrategyView.setParentView(combinedStrategiesView);
        newCombinedStrategyView.setCombinedStrategy(new VMCombinedStrategy());
        newCombinedStrategyView.update();
        newCombinedStrategyView.show();
    }
}
