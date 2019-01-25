package de.sswis.controller.handlers;


import de.sswis.controller.FileManager;
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

    /**
     *
     * @param combinedStrategyView die View mit der zu speichernden {@code kombinierten Strategie}
     */
    public SaveCombinedStrategiesHandler(AbstractNewCombinedStrategyView combinedStrategyView) {
        this.combinedStrategyView = combinedStrategyView;
        this.fileManager = new FileManager();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        VMCombinedStrategy combinedStrategy = this.combinedStrategyView.getCombinedStrategy();
        try {
            this.fileManager.saveCombinedStrategy(combinedStrategy);
        } catch (IOException e1) {
            e1.printStackTrace();
            return;
        }
        AbstractManageCombinedStrategiesView parentView = this.combinedStrategyView.getParentView();
        parentView.addStrategy(combinedStrategy);
        parentView.update();
        this.combinedStrategyView.close();
    }
}
