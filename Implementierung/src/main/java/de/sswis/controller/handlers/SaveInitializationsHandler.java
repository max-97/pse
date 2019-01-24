package de.sswis.controller.handlers;

import de.sswis.controller.FileManager;
import de.sswis.view.AbstractManageInitializationsView;
import de.sswis.view.AbstractNewInitializationView;
import de.sswis.view.model.VMInitialization;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

/**
 * Speichert die erstellte {@code Initialisierung}. Die View, die diesen {@code ActionListener} verwendet muss eine
 * {@code Initialisierung} besitzen.
 *
 * @author Max Braun
 */
public class SaveInitializationsHandler implements ActionListener {

    private AbstractNewInitializationView initializationView;
    private FileManager fileManager;

    /**
     *
     * @param initializationView die View mit der zu speichernden Initialisierung
     */
    public SaveInitializationsHandler(AbstractNewInitializationView initializationView) {
        this.initializationView = initializationView;
        this.fileManager = new FileManager();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        VMInitialization vmInitialization = this.initializationView.getVMInitialization();
        try {
            this.fileManager.saveInitalization(vmInitialization);
        } catch (IOException e1) {
            e1.printStackTrace();
            return;
        }
        AbstractManageInitializationsView parentView = this.initializationView.getParentView();
        if (parentView == null)
            return;
        parentView.addInit(vmInitialization);
        parentView.update();
        this.initializationView.close();
    }
}
