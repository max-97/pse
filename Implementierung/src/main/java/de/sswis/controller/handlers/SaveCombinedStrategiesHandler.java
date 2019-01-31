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
        try {
            this.fileManager.saveCombinedStrategy(combinedStrategy);
        } catch (IOException e1) {
            e1.printStackTrace();
            return;
        }
        CombinedStrategy cs = this.parser.parseVMCombinedStrategy(combinedStrategy);
        ModelProvider.getInstance().addCombinedStrategy(cs);
        ModelProvider.getInstance().addStrategy(cs);
        AbstractManageCombinedStrategiesView parentView = this.combinedStrategyView.getParentView();
        if (parentView == null)
            return;
        parentView.addStrategy(combinedStrategy);
        parentView.update();
        this.combinedStrategyView.close();
    }
}
