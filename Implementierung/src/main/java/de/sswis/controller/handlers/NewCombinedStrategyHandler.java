package de.sswis.controller.handlers;

import de.sswis.controller.ModelServiceLoader;
import de.sswis.model.conditions.Condition;
import de.sswis.model.strategies.BaseStrategy;
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
    private ModelServiceLoader serviceLoader;

    /**
     *
     * @param factory Fabrik zum Erstellen der View
     */
    public NewCombinedStrategyHandler(AbstractGuiFactory factory, AbstractManageCombinedStrategiesView view) {
        this.factory = factory;
        this.combinedStrategiesView = view;
        this.serviceLoader = new ModelServiceLoader();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        AbstractNewCombinedStrategyView newCombinedStrategyView = this.factory.createNewCombinedStrategyView();
        newCombinedStrategyView.setParentView(combinedStrategiesView);
        newCombinedStrategyView.setCombinedStrategy(new VMCombinedStrategy());
        for(Condition c : this.serviceLoader.getConditionList()) {
            newCombinedStrategyView.addCondition(c.getName());
        }
        for(BaseStrategy s : this.serviceLoader.getBaseStrategyList()) {
            newCombinedStrategyView.addBaseStrategy(s.getName());
        }
        newCombinedStrategyView.update();
        newCombinedStrategyView.show();
    }
}
