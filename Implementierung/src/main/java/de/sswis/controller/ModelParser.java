package de.sswis.controller;


import de.sswis.model.*;
import de.sswis.view.model.*;

import java.util.ArrayList;
import java.util.Collection;

/**
 * Übersetzt Objekte zwischen dem {@code ViewModel} und dem {@code Model}.
 * Dies dient primär dem Umsetzen von Benutzereingaben in eine lauffähige Simulation.
 * Außerdem können Ergebnisse abgelaufener Simulationen für die Ergebnisansicht aufbereitet werden.
 *
 * @author Simon Hügel
 */
public class ModelParser {

    /**
     * Übersetzt das {@code Simulation}-Objekt einer abgeschlossenen Simulation in ein {@code VMResult}-Objekt.
     * Dabei werden die Daten der Simulation in ein für die Ergebnisansicht konformes Format konvertiert.
     *
     * @param simulation die abgeschlossene Simulation
     * @return die für die Ergebnisansicht aufbereiteten Ergebnisse
     */
    public VMResult parseSimulationToVMResult(Simulation simulation) {
        return null;
    }

    /**
     * Übersetzt ein {@code VMCombinedStrategy}-Objekt in ein {@code CombinedStrategy}-Objekt.
     *
     * @param vmCombinedStrategy die zu übersetzende {@code VMCombinedStrategy}
     * @return die übersetzte {@code CombinedStrategy}
     */
    public CombinedStrategy parseVMCombinedStrategyToCombinedStrategy(VMCombinedStrategy vmCombinedStrategy) { return null; }

    /**
     * Übersetzt ein {@code VMConfiguration}-Objekt in eine Sammlung an {@code Configuration}-Objekten.
     * Im Falle einer Mehrfachkonfiguration enthält die zurückgegebene Sammlung die {@code n} Einzelkonfigurationen.
     *
     * @param vmConfig die zu übersetzende {@code VMConfiguration}
     * @return die übersetzte {@code Collection<Configuration>}
     */
    public Collection<Configuration> parseVMConfigurationToConfigurations(VMConfiguration vmConfig) {

        /*Configuration config = new Configuration(
                parseVMGameToGame(VMGame vmConfig.getGame()),
                ...
        )
        return config;*/
        return null;
    }

    /**
     * Übersetzt ein {@code VMGame}-Objekt in ein {@code Game}-Objekt.
     *
     * @param vmGame das zu übersetzende {@code VMGame}
     * @return das übersetzte {@code Game}
     */
    public Game parseVMGameToGame(VMGame vmGame) {

        Game game = new Game(
                vmGame.getName(),
                vmGame.getDescription(),
                parseVMGamePayoffsToGamePayoffs(vmGame));

        return null; }

    private Game.Tuple[][] parseVMGamePayoffsToGamePayoffs(VMGame vmGame) {

        Game.Tuple[][] outPayoffs = new Game.Tuple[2][2];
        int[][] inPayoffs = vmGame.getPayoffs();

        for (int row = 0; row < 2; row ++)
            for (int col = 0; col < 2; col++)
                outPayoffs[row][col] = new Game.Tuple(inPayoffs[row][(2*col)], inPayoffs[row][(2*col)+1]);

        return outPayoffs; }

    /**
     * Übersetzt ein {@code VMGroup}-Objekt in ein {@code Group}-Objekt.
     *
     * @param vmGroup die zu übersetzende {@code VMGroup}
     * @return die übersetzte {@code Group}
     */
    public Group parseVMGroupToGroup(VMGroup vmGroup) { return null; }

    /**
     * Übersetzt ein {@code VMInitialization}-Objekt in eine Sammlung an {@code Initialization}-Objekten.
     * Im Falle einer Mehrfachinitialisierung enthält die zurückgegebene Sammlung die {@code n} Einzelinitialisierungen.
     *
     * @param vmInitialization die zu übersetzende {@code VMInitialization}
     * @return die übersetzte {@code Collection<Initialization>}
     */
    public Collection<Initialization> parseVMInitializationToInitialization(VMInitialization vmInitialization) {

        Collection<Initialization> initializations = new ArrayList<>();

        return initializations; }

    private Initialization parseSingleVMInitializationToInitialization(VMInitialization vmInitialization) {

        Initialization initialization = new Initialization(
                vmInitialization.getName(),
                vmInitialization.getAgentCount());
        /*
        initialization.setGroupDistribution(
                AgentDistribution distribution,
                Group group);
        initialization.setStrategyDistribution(
                AgentDistribution distribution,
                CombinedStrategy strategy,
                Group group);
        initialization.setStrategyDistribution(
                AgentDistribution distribution,
                CombinedStrategy strategy,
                Group group);
        */
        return initialization;
    }

    /**
     * Übersetzt ein {@code VMStrategy}-Objekt in ein {@code Strategy}-Objekt.
     *
     * @param vmStrategy die zu übersetzende {@code VMStrategy}
     * @return die übersetzte {@code Strategy}
     */
    public Strategy parseVMStrategyToStrategy(VMStrategy vmStrategy) { return null; }





}
