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
    private ModelProvider provider;

    /**
     *
     * @param mainView Hauptfenster mit den ausgewählten {@code Konfigurationen}
     * @param factory Fabrik zum Erstellen der View
     */
    public ShowResultsHandler(AbstractMainView mainView, AbstractGuiFactory factory) {
        this.factory = factory;
        this.mainView = mainView;
        this.parser = new ModelParser();
        this.provider = ModelProvider.getInstance();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        VMConfiguration selected = mainView.getSelected();

        if (selected.isMultiConfiguration()) {
            AbstractShowMultiResultView multiResultsView = this.factory.createMultiResultsView();
            multiResultsView.setParentView(mainView);
            Collection<VMResult> results = selected.getResults();
            for (VMResult r : results) {
                multiResultsView.addVMResult(r);
            }
            multiResultsView.show();
        } else {
            AbstractShowResultView resultView = this.factory.createShowResultView();
            resultView.setParentView(mainView);
            Collection<VMResult> results = selected.getResults();
            for (VMResult r : results) {
                resultView.addVMResult(r);
            }
            resultView.show();
        }
    }
}
