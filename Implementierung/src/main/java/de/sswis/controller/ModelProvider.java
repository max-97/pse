package de.sswis.controller;

import de.sswis.exceptions.DuplicateObjectNameException;
import de.sswis.model.*;

import java.util.HashMap;
import java.util.Map;

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
    private HashMap<String, Strategy> strategies;

    private ModelProvider() {
        this.configurations = new HashMap<>();
        this.games = new HashMap<>();
        this.combinedStrategies = new HashMap<>();
        this.initialization = new HashMap<>();
        this.strategies = new HashMap<>();
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
    public void addConfiguration(Configuration configuration) {
        if(this.configurations.containsKey(configuration.getName())) {
            if(this.configurations.get(configuration.getName()).equals(configuration)) {
                // configuration already added, do nothing
                return;
            } else {
                throw new DuplicateObjectNameException("Another Configuration object with the same name already exists." +
                        "Duplicate names should be checked before object creation!");
            }
        }
        this.configurations.put(configuration.getName(), configuration);
    }


    /**
     * Fügt ein {@code Game} hinzu.
     *
     * @param game das zu speichernde {@code Game}
     */
    public void addGame(Game game) {
        if(this.games.containsKey(game.getName())) {
            if(this.games.get(game.getName()).equals(game)) {
                // game already added, do nothing
                return;
            } else {
                throw new DuplicateObjectNameException("Another Game object with the same name already exists." +
                        "Duplicate names should be checked before object creation!");
            }
        }
        this.games.put(game.getName(), game);
    }


    /**
     * Fügt eine {@code CombinedStrategy} hinzu.
     *
     * @param combStrategy die zu speichernde {@code CombinedStrategy}
     */
    public void addCombinedStrategy(CombinedStrategy combStrategy) {
        if(this.combinedStrategies.containsKey(combStrategy.getName())) {
            if(this.combinedStrategies.get(combStrategy.getName()).equals(combStrategy)) {
                // combinedStrategy already added, do nothing
                return;
            } else {
                throw new DuplicateObjectNameException("Another CombinedStrategy object with the same name already exists." +
                        "Duplicate names should be checked before object creation!");
            }
        }
        this.combinedStrategies.put(combStrategy.getName(), combStrategy);
    }


    /**
     * Fügt eine {@code Initialization} hinzu.
     *
     * @param initialization die zu speichernde {@code Initialization}
     */
    public void addInitialization(Initialization initialization) {
        if(this.initialization.containsKey(initialization.getName())) {
            if(this.initialization.get(initialization.getName()).equals(initialization)) {
                // initialization already added, do nothing
                return;
            } else {
                throw new DuplicateObjectNameException("Another Initialization object with the same name already exists." +
                        "Duplicate names should be checked before object creation!");
            }
        }
        this.initialization.put(initialization.getName(), initialization);
    }

    /**
     * Fügt eine {@code Strategy} hinzu
     *
     * @param strategy die zu speichernde {@code Strategy}
     */
    public void addStrategy(Strategy strategy) {
        if(this.strategies.containsKey(strategy.getName())) {
            if(this.strategies.get(strategy.getName()).equals(strategy)) {
                // strategies already added, do nothing
                return;
            } else {
                throw new DuplicateObjectNameException("Another Strategy object with the same name already exists." +
                        "Duplicate names should be checked before object creation!");
            }
        }
        this.strategies.put(strategy.getName(), strategy);
    }

    /**
     * Löscht eine {@code Configuration}. Die {@code Configuration} wird über ihren Namen identifiziert.
     * Löschen kann nicht rückgängig gemacht werden.
     *
     * @param name Name der zu löschenden {@code Configuration}
     */
    public void deleteConfiguration(String name) {
        this.configurations.remove(name);
    }

    /**
     * Löscht ein {@code Game}. Das {@code Game} wird über seinen Namen identifiziert.
     * Löschen kann nicht rückgängig gemacht werden.
     *
     * @param name Name des zu löschende {@code Game}
     */
    public void deleteGame(String name) {
        this.games.remove(name);
    }

    /**
     * Löscht eine {@code CombinedStrategy}. Die {@code CombinedStrategy} wird über ihren Namen identifiziert.
     * Löschen kann nicht rückgängig gemacht werden.
     *
     * @param name Name der zu löschenden {@code CombinedStrategy}
     */
    public void deleteCombinedStrategy(String name) {
        this.combinedStrategies.remove(name);
    }

    /**
     * Löscht eine {@code Initialization}. Die {@code Initialization} wird über ihren Namen identifiziert.
     * Löschen kann nicht rückgängig geamacht werden.
     *
     * @param name Name der zu löschenden {@code Initialization}
     */
    public void deleteInitialization(String name) {
        this.initialization.remove(name);
    }

    /**
     * Löscht eine {@code Strategy}. Die {@code Strategy} wird über ihren Namen identifiziert.
     * Löschen kann nicht rückgängig gemacht werden.
     *
     * @param name Name der zu löschenden {@code Strategy}
     */
    public void deleteStrategy(String name) {
        this.strategies.remove(name);
    }

    public Map<String, Configuration> getConfigurations() {
        return configurations;
    }

    public Configuration getConfiguration(String name) {
        return this.configurations.get(name);
    }

    public Map<String, Game> getGames() {
        return games;
    }

    public Game getGame(String name) {
        return this.games.get(name);
    }

    public Map<String, CombinedStrategy> getCombinedStrategies() {
        return combinedStrategies;
    }

    public CombinedStrategy getCombinedStrategy(String name) {
        return this.combinedStrategies.get(name);
    }

    public Map<String, Initialization> getInitializations() {
        return initialization;
    }

    public Initialization getInitialization(String name) {
        return this.initialization.get(name);
    }

    public Map<String, Strategy> getStrategies() {
        return this.strategies;
    }

    public Strategy getStrategy(String name) {
        return this.strategies.get(name);
    }

}
