package de.sswis.controller.handlers;

import de.sswis.controller.FileManager;
import de.sswis.controller.ModelServiceLoader;
import de.sswis.model.algorithms.adaptation.AdaptationAlgorithm;
import de.sswis.model.algorithms.pairing.PairingAlgorithm;
import de.sswis.model.algorithms.ranking.RankingAlgorithm;
import de.sswis.view.AbstractGuiFactory;
import de.sswis.view.AbstractMainView;
import de.sswis.view.AbstractNewConfigurationView;
import de.sswis.view.model.VMConfiguration;
import de.sswis.view.model.VMGame;
import de.sswis.view.model.VMInitialization;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;

/**
 * Öffnet eine View zum Erstellen einer {@code Konfiguration}.
 *
 * @author Max Braun
 */
public class NewConfigurationViewHandler implements ActionListener {

    private AbstractMainView mainView;
    private AbstractGuiFactory factory;
    private ModelServiceLoader serviceLoader;
    private FileManager fileManager;

    /**
     *
     * @param factory Fabrik zum Erstellen der View
     */
    public NewConfigurationViewHandler(AbstractGuiFactory factory, AbstractMainView main) {
        this.factory = factory;
        this.serviceLoader = new ModelServiceLoader();
        this.fileManager = new FileManager();
        this.mainView = main;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        AbstractNewConfigurationView newConfigurationView = this.factory.createNewConfigurationView();
        newConfigurationView.setParentView(null);
        newConfigurationView.setMainView(mainView);
        newConfigurationView.setConfiguration(new VMConfiguration());
        for (AdaptationAlgorithm a : this.serviceLoader.getAdaptAlgorithmList()) {
            HashMap<String, String[]> parameters = new HashMap<>();
            parameters.put(a.getName(), a.getParameters());
            newConfigurationView.addParameters(parameters);
            newConfigurationView.addAdaptionAlgorithm(a.getName());
        }
        for (PairingAlgorithm p : this.serviceLoader.getPairAlgorithmList()) {
            HashMap<String, String[]> parameters = new HashMap<>();
            parameters.put(p.getName(), p.getParameters());
            newConfigurationView.addParameters(parameters);
            newConfigurationView.addPairingAlgorithm(p.getName());
        }
        for (RankingAlgorithm r : this.serviceLoader.getRankAlgorithmList()) {
            HashMap<String, String[]> parameters = new HashMap<>();
            parameters.put(r.getName(), r.getParameters());
            newConfigurationView.addParameters(parameters);
            newConfigurationView.addRankingAlgorithm(r.getName());
        }
        for (VMInitialization i : this.fileManager.loadAllInitializations()) {
            newConfigurationView.addInitialization(i.getName());
        }
        for (VMGame g : this.fileManager.loadAllGames()) {
            newConfigurationView.addGame(g.getName());
        }
        newConfigurationView.show();
    }
}
