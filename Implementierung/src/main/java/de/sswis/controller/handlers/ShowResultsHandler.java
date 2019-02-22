package de.sswis.controller.handlers;

import de.sswis.controller.FileManager;
import de.sswis.controller.ModelParser;
import de.sswis.controller.ModelProvider;
import de.sswis.model.Configuration;
import de.sswis.view.AbstractGuiFactory;
import de.sswis.view.AbstractMainView;
import de.sswis.view.AbstractShowMultiResultView;
import de.sswis.view.AbstractShowResultView;
import de.sswis.view.model.VMConfiguration;
import de.sswis.view.model.VMInitialization;
import de.sswis.view.model.VMResult;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.util.Collection;

/**
 * Öffnet die View mit der Ergebnissansicht der ausgewählten {@code Konfigurationen}. Die {@code Konfigurationen} werden
 * im Hauptfenster ausgewählt.
 *
 * @author Max Braun
 */
public class ShowResultsHandler implements ActionListener {

    private AbstractGuiFactory factory;
    private AbstractMainView mainView;
    private ModelParser parser;

    /**
     *
     * @param mainView Hauptfenster mit den ausgewählten {@code Konfigurationen}
     * @param factory Fabrik zum Erstellen der View
     */
    public ShowResultsHandler(AbstractMainView mainView, AbstractGuiFactory factory) {
        this.factory = factory;
        this.mainView = mainView;
        this.parser = new ModelParser();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        VMConfiguration selected = mainView.getSelected();
        if (selected.isMultiConfiguration()) {
            AbstractShowMultiResultView multiResultsView = this.factory.createMultiResultsView();
            multiResultsView.setParentView(mainView);
            String init = selected.getInit();
            FileManager fileManager = new FileManager();
            VMInitialization initialization;
            try {
                initialization = fileManager.loadInitialization(init);
            } catch (FileNotFoundException e1) {
                return;
            }
            int numberOfInstances = initialization.getNumberOfInstances();
            for (int i = 1; i <= numberOfInstances; i++) {
                Configuration c = ModelProvider.getInstance().getConfiguration(selected.getName() + i);
                Collection<VMResult> vmResults = this.parser.parseSimulationToVMResult(c.getSimulation());
                for (VMResult r : vmResults) {
                    multiResultsView.addVMResult(r);
                }
                multiResultsView.show();
            }
        } else {
            AbstractShowResultView resultView = this.factory.createShowResultView();
            resultView.setParentView(mainView);
            Configuration c = ModelProvider.getInstance().getConfiguration(selected.getName());
            Collection<VMResult> vmResults = this.parser.parseSimulationToVMResult(c.getSimulation());
            for (VMResult r : vmResults) {
                resultView.addVMResult(r);
            }
            resultView.show();
        }
    }
}
