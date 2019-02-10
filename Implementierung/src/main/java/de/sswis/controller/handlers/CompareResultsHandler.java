package de.sswis.controller.handlers;

import de.sswis.controller.ModelParser;
import de.sswis.controller.ModelProvider;
import de.sswis.model.Configuration;
import de.sswis.view.*;
import de.sswis.view.model.VMConfiguration;
import de.sswis.view.model.VMResult;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Öffnet die View zum Vergleichen von Ergebnissen.
 *
 * @author Max Braun
 */
public class CompareResultsHandler implements ActionListener {

    private AbstractGuiFactory factory;
    private AbstractShowResultView showResultView;

    /**
     *
     * @param factory Fabrik zum Erstellen der View
     */
    public CompareResultsHandler(AbstractGuiFactory factory, AbstractShowResultView resultView) {
        this.factory = factory;
        showResultView = resultView;
    }

    public CompareResultsHandler(AbstractGuiFactory facotry, AbstractShowMultiResultView multiResultView) {
        this.factory = facotry;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        AbstractShowCompareView compareResultsView = this.factory.createCompareResultsView();
        AbstractMainView mainView = showResultView.getParentView();

        List<VMConfiguration> vmConfigurations = mainView.getVMConfigurations();
        ModelProvider provider = ModelProvider.getInstance();
        ModelParser parser = new ModelParser();
        for (VMConfiguration c : vmConfigurations) {
            if (!c.hasResult()) {
                continue;
            }
            Configuration configuration = provider.getConfiguration(c.getName());
            Collection<VMResult> vmResults = parser.parseSimulationToVMResult(configuration.getSimulation());
            ArrayList<VMResult> resultsCopy = new ArrayList<>();
            resultsCopy.addAll(vmResults);
            compareResultsView.addVMResultList(c.getName(), resultsCopy);
        }
        compareResultsView.show();
    }
}
