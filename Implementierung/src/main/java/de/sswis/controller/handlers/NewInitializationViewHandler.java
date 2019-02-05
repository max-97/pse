package de.sswis.controller.handlers;

import de.sswis.controller.FileManager;
import de.sswis.view.AbstractGuiFactory;
import de.sswis.view.AbstractNewInitializationView;
import de.sswis.view.model.VMCombinedStrategy;
import de.sswis.view.model.VMInitialization;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Öffnet eine View zum Erstellen einer {@code Initialisierung}.
 *
 * @author Max Braun
 */
public class NewInitializationViewHandler implements ActionListener {

    private AbstractGuiFactory factory;
    private FileManager fileManager;

    /**
     *
     * @param factory Fabrik zum Erstellen der View
     */
    public NewInitializationViewHandler(AbstractGuiFactory factory) {
        this.factory = factory;
        this.fileManager = new FileManager();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        AbstractNewInitializationView newInitializationView = this.factory.createNewInitializationView();
        newInitializationView.setParentView(null);
        newInitializationView.setInitialization(new VMInitialization());
        for (VMCombinedStrategy c : this.fileManager.loadAllCombinedStrategies()) {
            newInitializationView.addStrategy(c.getName());
        }
        newInitializationView.show();
    }
}
