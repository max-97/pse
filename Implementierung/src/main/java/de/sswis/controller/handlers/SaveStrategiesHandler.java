package de.sswis.controller.handlers;

import de.sswis.controller.FileManager;
import de.sswis.controller.ModelParser;
import de.sswis.controller.ModelProvider;
import de.sswis.model.MixedStrategy;
import de.sswis.view.AbstractManageStrategiesView;
import de.sswis.view.AbstractNewStrategyView;
import de.sswis.view.model.VMStrategy;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;


/**
 * Speichert die erstellte {@code gemischte Strategie}. Die View, die diesen {@code ActionListener} verwendet muss
 * eine {@code gemischte Strategie} besitzen.
 *
 * @author Max Braun
 */
public class SaveStrategiesHandler implements ActionListener {

    private AbstractNewStrategyView strategyView;
    private FileManager fileManager;
    private ModelParser parser;

    /**
     *
     * @param strategyView die View mit der zu speichernden {@code kombinierten Strategie}
     */
    public SaveStrategiesHandler(AbstractNewStrategyView strategyView) {
        this.strategyView = strategyView;
        this.fileManager = new FileManager();
        this.parser = new ModelParser();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        VMStrategy vmStrategy = this.strategyView.getVMStrategy();
        try {
            this.fileManager.saveMixedStrategy(vmStrategy);
        } catch (IOException e1) {
            e1.printStackTrace();
            return;
        }
        MixedStrategy mixedStrategy = this.parser.parseVMStrategy(vmStrategy);
        ModelProvider.getInstance().addMixedStrategy(mixedStrategy);

        AbstractManageStrategiesView parentView = this.strategyView.getParentView();
        this.strategyView.close();
        if (parentView == null) {
            return;
        }
        parentView.addStrategy(vmStrategy);
    }
}
