package de.sswis.controller.handlers;

import de.sswis.controller.FileManager;
import de.sswis.controller.ModelServiceLoader;
import de.sswis.model.conditions.Condition;
import de.sswis.model.strategies.BaseStrategy;
import de.sswis.view.AbstractGuiFactory;
import de.sswis.view.AbstractManageStrategiesView;
import de.sswis.view.AbstractNewStrategyView;
import de.sswis.view.model.VMCombinedStrategy;
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
    private FileManager fileManager;

    /**
     *
     * @param factory Fabrik zum Erstellen der View
     */
    public EditStrategyHandler(AbstractGuiFactory factory, AbstractManageStrategiesView view) {
        this.factory = factory;
        this.manageStrategiesView = view;
        this.fileManager = new FileManager();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        AbstractNewStrategyView newStrategyView = this.factory.createNewStrategyView();
        newStrategyView.setParentView(this.manageStrategiesView);
        VMStrategy selectedVM = this.manageStrategiesView.getSelectedVM();
        newStrategyView.setStrategy(selectedVM);
        VMStrategy editedStrategy = new VMStrategy();
        editedStrategy.setName(selectedVM.getName());
        manageStrategiesView.setEditedStrategy(editedStrategy);
        for (VMCombinedStrategy c : this.fileManager.loadAllCombinedStrategies()) {
            newStrategyView.addCombinedStrategy(c.getName());
        }
        newStrategyView.show();
    }
}
