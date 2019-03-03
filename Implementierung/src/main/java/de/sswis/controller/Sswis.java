package de.sswis.controller;

import de.sswis.model.*;
import de.sswis.view.AbstractMainView;
import de.sswis.view.SwingGuiFactory;
import de.sswis.view.model.*;

import java.util.Collection;

public class Sswis {

    private Sswis() {
        ModelProvider provider = ModelProvider.getInstance();
        ModelParser parser = new ModelParser();
        FileManager fileManager = new FileManager();
        SwingGuiFactory factory = new SwingGuiFactory();

        AbstractMainView mainView = factory.createMainView();

        Collection<VMGame> vmGames = fileManager.loadAllGames();
        for (VMGame g : vmGames) {
            Game game = parser.parseVMGame(g);
            provider.addGame(game);
        }

        Collection<VMCombinedStrategy> vmCombinedStrategies = fileManager.loadAllCombinedStrategies();
        for (VMCombinedStrategy cs : vmCombinedStrategies) {
            CombinedStrategy combinedStrategy = parser.parseVMCombinedStrategy(cs);
            provider.addCombinedStrategy(combinedStrategy);
        }

        Collection<VMStrategy> vmStrategies = fileManager.loadAllMixedStrategies();
        for (VMStrategy s : vmStrategies) {
            MixedStrategy strategy = parser.parseVMStrategy(s);
            provider.addMixedStrategy(strategy);
        }

        Collection<VMInitialization> vmInitializations = fileManager.loadAllInitializations();
        for (VMInitialization i : vmInitializations) {
            Collection<Initialization> initializations = parser.parseVMInitialization(i);
            for (Initialization init : initializations) {
                provider.addInitialization(init);
            }
        }

        Collection<VMConfiguration> vmConfigurations = fileManager.loadAllConfigurations();
        for (VMConfiguration c : vmConfigurations) {
            Collection<Configuration> configurations = parser.parseVMConfiguration(c);
            mainView.addConfiguration(c);
            for (Configuration config : configurations) {
                provider.addConfiguration(config);
            }
        }

        Collection<Collection<VMResult>> vmResults = fileManager.loadAllResults();
        for (Collection<VMResult> r : vmResults) {
            VMResult first = r.iterator().next();
            mainView.setResults(first.getName().replaceFirst("_\\d+", ""), r);
        }
        mainView.show();
    }

    public static void main(String[] args) {
        new Sswis();
    }
}
