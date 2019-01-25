package de.sswis.controller.handlers;

import de.sswis.controller.ModelServiceLoader;
import de.sswis.model.conditions.Condition;
import de.sswis.model.strategies.BaseStrategy;
import de.sswis.view.AbstractGuiFactory;
import de.sswis.view.AbstractMainView;
import de.sswis.view.AbstractNewCombinedStrategyView;
import de.sswis.view.model.VMCombinedStrategy;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Öffnet eine View zum Erstellen einer {@code kombinierte Strategie}.
 *
 * @author Max Braun
 */
public class NewCombinedStrategyViewHandler implements ActionListener {

    private AbstractGuiFactory factory;
    private ModelServiceLoader serviceLoader;

    /**
     *
     * @param factory Fabrik zum Erstellen der View
     */
    public NewCombinedStrategyViewHandler(AbstractGuiFactory factory) {
        this.factory = factory;
        this.serviceLoader = new ModelServiceLoader();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        AbstractNewCombinedStrategyView newCombinedStrategyView = this.factory.createNewCombinedStrategyView();
        newCombinedStrategyView.setParentView(null);
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
