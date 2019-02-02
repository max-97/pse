package de.sswis.controller.handlers;

import de.sswis.controller.FileManager;
import de.sswis.view.AbstractGuiFactory;
import de.sswis.view.AbstractMainView;
import de.sswis.view.AbstractManageResultsView;
import de.sswis.view.model.VMResult;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Öffnet die View zum Verwalten der {@code Ergebnisse}.
 *
 * @author Max Braun
 */
public class ManageResultsHandler implements ActionListener {

    private AbstractGuiFactory factory;
    private AbstractMainView mainView;
    /**
     *
     * @param factory Fabrik zum Erstellen der View
     */
    public ManageResultsHandler(AbstractGuiFactory factory, AbstractMainView view) {
        this.factory = factory;
        this.mainView = view;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        AbstractManageResultsView manageResultsView = this.factory.createManageResultsView();
        manageResultsView.setParentView(mainView);
        for(VMResult r : mainView.getResults()) {
            manageResultsView.addResult(r);
        }
        manageResultsView.show();
    }
}
