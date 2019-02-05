package de.sswis.controller.handlers;

import de.sswis.controller.FileManager;
import de.sswis.view.AbstractGuiFactory;
import de.sswis.view.AbstractManageInitializationsView;
import de.sswis.view.AbstractNewInitializationView;
import de.sswis.view.model.VMCombinedStrategy;
import de.sswis.view.model.VMInitialization;
import de.sswis.view.model.VMStrategy;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Öffnet die View zum Bearbeiten einer {@code Initialisierung}.
 *
 * @author Max Braun
 */
public class EditInitializationHandler implements ActionListener {

    private AbstractGuiFactory factory;
    private AbstractManageInitializationsView manageInitializationsView;
    private FileManager fileManager;

    /**
     *
     * @param factory Fabrik zum Erstellen der View
     */
    public EditInitializationHandler(AbstractGuiFactory factory, AbstractManageInitializationsView view) {
        this.factory = factory;
        this.manageInitializationsView = view;
        this.fileManager = new FileManager();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        AbstractNewInitializationView newInitializationView = this.factory.createNewInitializationView();
        newInitializationView.setParentView(this.manageInitializationsView);
        for (VMCombinedStrategy c : this.fileManager.loadAllCombinedStrategies()) {
            newInitializationView.addStrategy(c.getName());
        }
        for (VMStrategy s : this.fileManager.loadAllMixedStrategies()) {
            newInitializationView.addStrategy(s.getName());
        }
        VMInitialization selectedVM = this.manageInitializationsView.getSelectedVM();
        newInitializationView.setInitialization(selectedVM);
        VMInitialization editedInitialization = new VMInitialization();
        editedInitialization.setName(selectedVM.getName());
        manageInitializationsView.setEditedInitialization(editedInitialization);
        newInitializationView.show();
    }
}
