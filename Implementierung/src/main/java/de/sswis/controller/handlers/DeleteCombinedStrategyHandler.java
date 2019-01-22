package de.sswis.controller.handlers;

import de.sswis.controller.FileManager;
import de.sswis.view.AbstractManageCombinedStrategiesView;
import de.sswis.view.model.VMCombinedStrategy;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Löscht die ausgewählte {@code kombinierte Strategie}.
 *
 * @author Max Braun
 */
public class DeleteCombinedStrategyHandler implements ActionListener {

    private AbstractManageCombinedStrategiesView manageStrategiesView;
    private FileManager fileManager;

    /**
     *
     * @param manageStrategiesView View, welche die zu löschende {@code CombinedStrategy} beinhaltet
     */
    public DeleteCombinedStrategyHandler(AbstractManageCombinedStrategiesView manageStrategiesView) {
        this.manageStrategiesView = manageStrategiesView;
        this.fileManager = new FileManager();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        VMCombinedStrategy selectedVM = manageStrategiesView.getSelectedVM();
        this.manageStrategiesView.removeStrategy(selectedVM.getName());
        this.manageStrategiesView.update();
        this.fileManager.deleteCombinedStrategy(selectedVM.getName());
    }
}
