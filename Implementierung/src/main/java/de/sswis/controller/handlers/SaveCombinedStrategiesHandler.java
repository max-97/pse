package de.sswis.controller.handlers;


import de.sswis.controller.FileManager;
import de.sswis.controller.ModelParser;
import de.sswis.controller.ModelProvider;
import de.sswis.model.CombinedStrategy;
import de.sswis.view.AbstractManageCombinedStrategiesView;
import de.sswis.view.AbstractNewCombinedStrategyView;
import de.sswis.view.model.VMCombinedStrategy;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;


/**
 * Speichert die erstellte {@code kombinierte Strategie}. Die View, die diesen {@code ActionListener} verwendet muss
 * eine {@code kombinierte Strategie} besitzen.
 *
 * @author Max Braun
 */
public class SaveCombinedStrategiesHandler implements ActionListener {

    private AbstractNewCombinedStrategyView combinedStrategyView;
    private FileManager fileManager;
    private ModelParser parser;

    /**
     *
     * @param combinedStrategyView die View mit der zu speichernden {@code kombinierten Strategie}
     */
    public SaveCombinedStrategiesHandler(AbstractNewCombinedStrategyView combinedStrategyView) {
        this.combinedStrategyView = combinedStrategyView;
        this.fileManager = new FileManager();
        this.parser = new ModelParser();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        VMCombinedStrategy combinedStrategy = this.combinedStrategyView.getCombinedStrategy();

        if (combinedStrategy != null) {
            try {
                this.fileManager.saveCombinedStrategy(combinedStrategy);
            } catch (IOException e1) {
                e1.printStackTrace();
                return;
            }
            CombinedStrategy cs = this.parser.parseVMCombinedStrategy(combinedStrategy);
            ModelProvider.getInstance().addCombinedStrategy(cs);
            AbstractManageCombinedStrategiesView parentView = this.combinedStrategyView.getParentView();
            this.combinedStrategyView.close();
            if (parentView == null) {
                return;
            }
            VMCombinedStrategy editedCombinedStrategy = parentView.getEditedCombinedStrategy();
            if (editedCombinedStrategy == null) {
                parentView.addStrategy(combinedStrategy);
            } else {
                parentView.replaceCombinedStrategy(combinedStrategy);
                if (!editedCombinedStrategy.getName().equals(combinedStrategy.getName())) {
                    ModelProvider.getInstance().deleteCombinedStrategy(editedCombinedStrategy.getName());
                    try {
                        this.fileManager.deleteCombinedStrategy(editedCombinedStrategy.getName());
                    } catch (IOException e1) {
                        e1.printStackTrace();
                    }
                }
                parentView.setEditedCombinedStrategy(null);
            }
        }
    }

}
