package de.sswis.controller.handlers;

import de.sswis.controller.FileManager;
import de.sswis.view.AbstractGuiFactory;
import de.sswis.view.AbstractMainView;
import de.sswis.view.AbstractManageCombinedStrategiesView;
import de.sswis.view.model.VMCombinedStrategy;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Collection;

/**
 * Öffnet die View zum Verwalten der {@code kombinierten Strategien}.
 *
 * @author Max Braun
 */
public class ManageCombinedStrategiesHandler implements ActionListener {

    private AbstractGuiFactory factory;
    private AbstractMainView mainView;
    private FileManager fileManager;

    /**
     *
     * @param factory Fabrik zum Erstellen der View
     */
    public ManageCombinedStrategiesHandler(AbstractGuiFactory factory, AbstractMainView view) {
        this.factory = factory;
        this.mainView = view;
        this.fileManager = new FileManager();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        AbstractManageCombinedStrategiesView manageCombinedStrategiesView = this.factory.createManageCombinedStrategiesView();
        manageCombinedStrategiesView.setParentView(this.mainView);
        for(VMCombinedStrategy c : this.fileManager.loadAllCombinedStrategies()) {
            manageCombinedStrategiesView.addStrategy(c);
        }
        manageCombinedStrategiesView.show();
    }
}
