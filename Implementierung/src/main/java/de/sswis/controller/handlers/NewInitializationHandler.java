package de.sswis.controller.handlers;

import de.sswis.controller.FileManager;
import de.sswis.view.AbstractGuiFactory;
import de.sswis.view.AbstractManageInitializationsView;
import de.sswis.view.AbstractNewInitializationView;
import de.sswis.view.model.VMCombinedStrategy;
import de.sswis.view.model.VMInitialization;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Erstellt eine neue Initialisierung. In der View zum Verwalten der {@code Initialisierungen} wird eine neue
 * {@code Initialisierung} hinzugefügt und es öffnet sich die View zum Bearbeiten der neuen {@code Initialisierung}.
 *
 * @author Max Braun
 */
public class NewInitializationHandler implements ActionListener {

    private AbstractGuiFactory factory;
    private AbstractManageInitializationsView initializationsView;
    private FileManager fileManager;

    /**
     *
     * @param factory Fabrik zum Erstellen der View
     */
    public NewInitializationHandler(AbstractGuiFactory factory, AbstractManageInitializationsView view) {
        this.factory = factory;
        this.initializationsView = view;
        this.fileManager = new FileManager();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        AbstractNewInitializationView newInitializationView = this.factory.createNewInitializationView();
        newInitializationView.setParentView(initializationsView);
        newInitializationView.setInitialization(new VMInitialization());
        for (VMCombinedStrategy c : this.fileManager.loadAllCombinedStrategies()) {
            newInitializationView.addCombinedStrategy(c.getName());
        }
        newInitializationView.show();
    }
}
