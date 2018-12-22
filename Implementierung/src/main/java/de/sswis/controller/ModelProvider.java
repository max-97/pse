package de.sswis.controller;

import de.sswis.model.CombinedStrategy;
import de.sswis.model.Configuration;
import de.sswis.model.Game;
import de.sswis.model.Initialization;

import java.util.HashMap;

/**
 * Verwaltet die Model-Objekte. Alle erstellten {@link Configuration Configurations}, {@link Game Games},
 * {@link CombinedStrategy CombinedStrategies} und {@link Initialization Initializatoins} werden hier gespeichert, damit
 * andere Controller-Objekte auf sie zugreifen können.
 * Alle Objekte werden über ihren zugewiesenen Namen indentifiziert.
 *
 * @author Max Braun
 */
public class ModelProvider {

    private static ModelProvider modelProvider;

    private HashMap<String, Configuration> configurations;
    private HashMap<String, Game> games;
    private HashMap<String, CombinedStrategy> combinedStrategies;
    private HashMap<String, Initialization> initialization;

    private ModelProvider() {

    }

    /**
     * Gibt die Instanz dieser Klasse zurück. Es wird immer die gleiche Instanz zurück gegeben.
     *
     * @return die Instanz dieser Klasse
     */
    public static ModelProvider getInstance() {
        if (modelProvider == null) {
            modelProvider = new ModelProvider();
        }
        return modelProvider;
    }

    /**
     * Fügt eine {@code Collection} hinzu.
     *
     * @param configuration die zu speichernde {@code Configurations}
     */
    public void addConfigurations(Configuration configuration) {

    }


    /**
     * Fügt ein {@code Game} hinzu.
     *
     * @param game das zu speichernde {@code Game}
     */
    public void addGame(Game game) {

    }


    /**
     * Fügt eine {@code CombinedStrategy} hinzu.
     *
     * @param combStrategy die zu speichernde {@code CombinedStrategy}
     */
    public void addCombinedStrategy(CombinedStrategy combStrategy) {

    }


    /**
     * Fügt eine {@code Initialization} hinzu.
     *
     * @param initialization die zu speichernde {@code Initialization}
     */
    public void addInitialization(Initialization initialization) {

    }

    /**
     * Löscht eine {@code Configuration}. Die {@code Configuration} wird über ihren Namen identifiziert.
     * Löschen kann nicht rückgängig gemacht werden.
     *
     * @param name Name der zu löschenden {@code Configuration}
     */
    public void deleteConfiguration(String name) {

    }

    /**
     * Löscht ein {@code Game}. Das {@code Game} wird über seinen Namen identifiziert.
     * Löschen kann nicht rückgängig gemacht werden.
     *
     * @param name Name des zu löschende {@code Game}
     */
    public void deleteGame(String name) {

    }

    /**
     * Löscht eine {@code CombinedStrategy}. Die {@code CombinedStrategy} wird über ihren Namen identifiziert.
     * Löschen kann nicht rückgängig gemacht werden.
     *
     * @param name Name der zu löschenden {@code CombinedStrategy}
     */
    public void deleteCombinedStrategy(String name) {

    }

    /**
     * Löscht eine {@code Initialization}. Die {@code Initialization} wird über ihren Namen identifiziert.
     * Löschen kann nicht rückgängig geamacht werden.
     *
     * @param name Name der zu löschenden {@code Initialization}
     */
    public void deleteInitialization(String name) {

    }

    /*
    public Map<String, Collection<Configuration>> getConfigurations() {
        return configurations;
    }

    public Configuration getConfiguration(String name) {
        return null;
    }

    public Map<String, Game> getGames() {
        return games;
    }

    public Game getGame(String name) {
        return null;
    }

    public Map<String, CombinedStrategy> getCombinedStrategies() {
        return combinedStrategies;
    }

    public CombinedStrategy getCombinedStrategy(String name) {
        return null;
    }

    public Map<String, Initialization> getInitializations() {
        return initialization;
    }

    public Initialization getInitialization(String name) {
        return null;
    }
    */

}
