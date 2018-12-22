package de.sswis.controller;


import de.sswis.model.*;
import de.sswis.view.model.*;

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
    public Game parseVMGameToGame(VMGame vmGame) { return null; }

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
    public Collection<Initialization> parseVMInitializationToInitialization(VMInitialization vmInitialization) { return null; }

    /**
     * Übersetzt ein {@code VMStrategy}-Objekt in ein {@code Strategy}-Objekt.
     *
     * @param vmStrategy die zu übersetzende {@code VMStrategy}
     * @return die übersetzte {@code Strategy}
     */
    public Strategy parseVMStrategyToStrategy(VMStrategy vmStrategy) { return null; }





}
