package de.sswis.controller.handlers;

import de.sswis.controller.FileManager;
import de.sswis.view.AbstractManageStrategiesView;
import de.sswis.view.model.VMStrategy;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

/**
 * Löscht die ausgewählte {@code gemischte Strategie}.
 *
 * @author Max Braun
 */
public class DeleteStrategyHandler implements ActionListener {

    private AbstractManageStrategiesView manageStrategiesView;
    private FileManager fileManager;

    /**
     *
     * @param manageStrategiesView View, welche die zu löschende {@code Strategy} beinhaltet
     */
    public DeleteStrategyHandler(AbstractManageStrategiesView manageStrategiesView) {
        this.manageStrategiesView = manageStrategiesView;
        this.fileManager = new FileManager();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        VMStrategy selectedVM = this.manageStrategiesView.getSelectedVM();
        try {
            this.fileManager.deleteMixedStrategy(selectedVM.getName());
        } catch (IOException e1) {
            e1.printStackTrace();
            return;
        }
        this.manageStrategiesView.removeStrategy(selectedVM.getName());
    }
}
