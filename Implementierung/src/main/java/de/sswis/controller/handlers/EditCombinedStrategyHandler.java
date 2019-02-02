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
import java.util.Collection;
import java.util.HashMap;

/**
 * Öffnet die View zum Bearbeiten einer {@code kombinierten Strategie}.
 *
 * @author Max Braun
 */
public class EditCombinedStrategyHandler implements ActionListener {

    private AbstractGuiFactory factory;
    private AbstractManageCombinedStrategiesView manageCombinedStrategiesView;
    private ModelServiceLoader serviceLoader;

    /**
     *
     * @param factory Fabrik zum Erstellen der View
     */
    public EditCombinedStrategyHandler(AbstractGuiFactory factory, AbstractManageCombinedStrategiesView view) {
        this.factory = factory;
        this.manageCombinedStrategiesView = view;
        this.serviceLoader = new ModelServiceLoader();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        AbstractNewCombinedStrategyView newCombinedStrategyView = this.factory.createNewCombinedStrategyView();
        newCombinedStrategyView.setParentView(manageCombinedStrategiesView);
        VMCombinedStrategy selectedVM = this.manageCombinedStrategiesView.getSelectedVM();
        newCombinedStrategyView.setCombinedStrategy(selectedVM);
        VMCombinedStrategy editedCombinedStrategy = new VMCombinedStrategy();
        editedCombinedStrategy.setName(selectedVM.getName());
        manageCombinedStrategiesView.setEditedCombinedStrategy(editedCombinedStrategy);
        for(Condition c : this.serviceLoader.getConditionList()) {
            newCombinedStrategyView.addCondition(c.getName());
            HashMap<String, String[]> parameters = new HashMap<>();
            parameters.put(c.getName(), c.getParameters());
            newCombinedStrategyView.addParameters(parameters);
        }
        for(BaseStrategy s : this.serviceLoader.getBaseStrategyList()) {
            newCombinedStrategyView.addBaseStrategy(s.getName());
        }
        newCombinedStrategyView.show();
    }
}
