package de.sswis.controller.handlers;

import de.sswis.controller.FileManager;
import de.sswis.controller.ModelServiceLoader;
import de.sswis.model.algorithms.adaptation.AdaptationAlgorithm;
import de.sswis.model.algorithms.pairing.PairingAlgorithm;
import de.sswis.model.algorithms.ranking.RankingAlgorithm;
import de.sswis.view.AbstractGuiFactory;
import de.sswis.view.AbstractManageConfigurationsView;
import de.sswis.view.AbstractNewConfigurationView;
import de.sswis.view.model.VMConfiguration;
import de.sswis.view.model.VMGame;
import de.sswis.view.model.VMInitialization;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Öffnet die View zum Bearbeiten einer {@code Konfiguration}.
 *
 * @author Max Braun
 */
public class EditConfigurationHandler implements ActionListener {

    private AbstractGuiFactory factory;
    private AbstractManageConfigurationsView manageConfigurationsView;
    private ModelServiceLoader serviceLoader;
    private FileManager fileManager;

    /**
     *
     * @param factory Fabrik zum Erstellen der View
     */
    public EditConfigurationHandler(AbstractGuiFactory factory, AbstractManageConfigurationsView view) {
        this.factory = factory;
        this.manageConfigurationsView = view;
        this.serviceLoader = new ModelServiceLoader();
        this.fileManager = new FileManager();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        AbstractNewConfigurationView newConfigurationView = this.factory.createNewConfigurationView();
        newConfigurationView.setParentView(this.manageConfigurationsView);
        VMConfiguration selectedVM = this.manageConfigurationsView.getSelectedVM();
        newConfigurationView.setConfiguration(selectedVM);
        for (AdaptationAlgorithm a : this.serviceLoader.getAdaptAlgorithmList()) {
            newConfigurationView.addAdaptionAlgorithm(a.getName());
        }
        for (PairingAlgorithm p : this.serviceLoader.getPairAlgorithmList()) {
            newConfigurationView.addPairingAlgorithm(p.getName());
        }
        for (RankingAlgorithm r : this.serviceLoader.getRankAlgorithmList()) {
            newConfigurationView.addRankingAlgorithm(r.getName());
        }
        for (VMInitialization i : this.fileManager.loadAllInitializations()) {
            newConfigurationView.addInitialization(i.getName());
        }
        for (VMGame g : this.fileManager.loadAllGames()) {
            newConfigurationView.addGame(g.getName());
        }
        newConfigurationView.update();
        newConfigurationView.show();
    }
}
