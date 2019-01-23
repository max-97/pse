package de.sswis.controller.handlers;

import de.sswis.controller.FileManager;
import de.sswis.view.AbstractManageStrategiesView;
import de.sswis.view.AbstractNewStrategyView;
import de.sswis.view.model.VMStrategy;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


/**
 * Speichert die erstellte {@code gemischte Strategie}. Die View, die diesen {@code ActionListener} verwendet muss
 * eine {@code gemischte Strategie} besitzen.
 *
 * @author Max Braun
 */
public class SaveStrategiesHandler implements ActionListener {

    private AbstractNewStrategyView strategyView;
    private FileManager fileManager;

    /**
     *
     * @param strategyView die View mit der zu speichernden {@code kombinierten Strategie}
     */
    public SaveStrategiesHandler(AbstractNewStrategyView strategyView) {
        this.strategyView = strategyView;
        this.fileManager = new FileManager();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        VMStrategy vmStrategy = this.strategyView.getVMStrategy();
        this.fileManager.saveMixedStrategy(vmStrategy);
        AbstractManageStrategiesView parentView = this.strategyView.getParentView();
        parentView.addStrategy(vmStrategy);
        parentView.update();
        this.strategyView.close();
    }
}
