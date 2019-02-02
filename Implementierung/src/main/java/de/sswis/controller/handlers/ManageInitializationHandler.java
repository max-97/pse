package de.sswis.controller.handlers;

import de.sswis.controller.FileManager;
import de.sswis.view.AbstractGuiFactory;
import de.sswis.view.AbstractMainView;
import de.sswis.view.AbstractManageInitializationsView;
import de.sswis.view.model.VMInitialization;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Öffnet die View zum Verwalten der {@code Initialisierungen}.
 *
 * @author Max Braun
 */
public class ManageInitializationHandler implements ActionListener {

    private AbstractGuiFactory factory;
    private AbstractMainView mainView;
    private FileManager fileManager;

    /**
     *
     * @param factory Fabrik zum Erstellen der View
     */
    public ManageInitializationHandler(AbstractGuiFactory factory, AbstractMainView view) {
        this.factory = factory;
        this.mainView = view;
        this.fileManager = new FileManager();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        AbstractManageInitializationsView manageInitializationsView = this.factory.createManageInitializationsView();
        manageInitializationsView.setParentView(this.mainView);
        for(VMInitialization i : this.fileManager.loadAllInitializations()) {
            manageInitializationsView.addInit(i);
        }
        manageInitializationsView.show();
    }
}
