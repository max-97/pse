package de.sswis.controller.handlers;

import de.sswis.controller.FileManager;
import de.sswis.view.AbstractGuiFactory;
import de.sswis.view.AbstractMainView;
import de.sswis.view.AbstractManageStrategiesView;
import de.sswis.view.model.VMStrategy;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Öffnet die View zum Verwalten der {@code gemischten Strategien}.
 *
 * @author Max Braun
 */
public class ManageStrategiesHandler implements ActionListener {

    private AbstractGuiFactory factory;
    private AbstractMainView mainView;
    private FileManager fileManager;

    /**
     *
     * @param factory Fabrik zum Erstellen der View
     */
    public ManageStrategiesHandler(AbstractGuiFactory factory, AbstractMainView view) {
        this.factory = factory;
        this.mainView = view;
        this.fileManager = new FileManager();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        AbstractManageStrategiesView manageStrategiesView = this.factory.createManageStrategiesView();
        manageStrategiesView.setParentView(mainView);
        for(VMStrategy s : this.fileManager.loadAllMixedStrageyies()) {
            manageStrategiesView.addStrategy(s);
        }
        manageStrategiesView.show();
    }
}
