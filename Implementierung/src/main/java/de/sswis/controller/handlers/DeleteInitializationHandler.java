package de.sswis.controller.handlers;

import de.sswis.controller.FileManager;
import de.sswis.view.AbstractManageInitializationsView;
import de.sswis.view.model.VMInitialization;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Löscht die ausgewählte {@code Initialisierung}.
 *
 * @author Max Braun
 */
public class DeleteInitializationHandler implements ActionListener {

    private AbstractManageInitializationsView manageInitializationsView;
    private FileManager fileManager;

    /**
     *
     * @param manageInitializationsView View, welche die zu löschende {@code Initialization} beinhaltet
     */
    public DeleteInitializationHandler(AbstractManageInitializationsView manageInitializationsView) {
        this.manageInitializationsView = manageInitializationsView;
        this.fileManager = new FileManager();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        VMInitialization selectedVM = this.manageInitializationsView.getSelectedVM();
        this.manageInitializationsView.removeInit(selectedVM.getName());
        this.manageInitializationsView.update();
        this.fileManager.deleteInitalization(selectedVM.getName());
    }
}
