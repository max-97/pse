package de.sswis.controller;


import de.sswis.model.*;
import de.sswis.view.model.*;

/**
 * Ein Kommunikator zum Austausch von Eingaben und Ergebnissen zwischen dem Model und dem ViewModel.
 *
 * @author Simon Hügel
 */
public class Communicator {

    public VMCombinedStrategy parseCombinedStrategyToVMCombinedStrategy(CombinedStrategy combinedStrategy) { return null; }
    /**
     * Gibt zu einer Simulation die zugrundeliegende Konfiguration in einem für die Benutzeroberfläche konformen Format zurück.
     *
     * @param simulation die Simulation
     * @return die der Simulation zugrundeliegende Konfiguration
     */
    public VMConfiguration parseSimulationToVMConfiguration(Simulation simulation) {
        return null;
    }
    public VMGame parseGameToVMGame(Game game) { return null; }
    public VMGroup parseGroupToVMGroup(Group group) { return null; }
    public VMInitialization parseInitializationToVMInitialization(Initialization initialization) { return null; }
    /**
     * Übersetzt die Daten einer abgeschlossenen Simulation in ein für die Ergebnisansicht konformes Format.
     *
     * @param simulation die abgeschlossene Simulation
     * @return die für die Ergebnisansicht aufbereiteten Ergebnisse
     */
    public VMResult parseSimulationToVMResult(Simulation simulation) {
        return null;
    }
    public VMStrategy parseStrategyToVMStrategy(Strategy strategy) { return null; }


    public CombinedStrategy parseVMCombinedStrategyToCombinedStrategy(VMCombinedStrategy vmCombinedStrategy) { return null; }
    /**
     * Übersetzt die Benutzereingaben zur Konfiguration in ein für das Ausführen der Simulation konformes Format.
     *
     * @param vmConfig die Benutzereingaben zur Konfiguration
     * @return die für die Simulation verwendete Konfiguration
     */
    public Configuration parseVMConfigToConfig(VMConfiguration vmConfig) {

        /*Configuration config = new Configuration(
                parseVMGameToGame(VMGame vmConfig.getGame()),
                ...
        )
        return config;*/
        return null;
    }
    public Game parseVMGameToGame(VMGame vmGame) { return null; }
    public Group parseVMGroupToGroup(VMGroup vmGroup) { return null; }
    public Initialization parseVMInitializationToInitialization(VMInitialization vmInitialization) { return null; }
    public Strategy parseVMStrategyToStrategy(VMStrategy vmStrategy) { return null; }





}
