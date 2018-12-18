package de.sswis.controller;

import de.sswis.model.*;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Max Braun
 */
public class ModelProvider {

    private static ModelProvider modelProvider;

    private HashMap<String, Collection<Configuration>> configurations;
    private HashMap<Integer, Agent> agents;
    private HashMap<String, Game> games;
    private HashMap<String, CombinedStrategy> combinedStrategies;
    private HashMap<String, Initialization> initialization;

    private ModelProvider() {

    }

    public static ModelProvider getInstance() {
        if (modelProvider == null) {
            modelProvider = new ModelProvider();
        }
        return modelProvider;
    }

    public void addConfigurations(Collection<Configuration> configurations) {

    }

    public void addAgent(Agent agent) {

    }

    public void addGame(Game game) {

    }

    public void addCombinedStrategy(CombinedStrategy combStrategy) {

    }

    public void addInitialization(Initialization initialization) {

    }

    public void deleteConfigurations(String name) {

    }

    public void deleteAgents(int id) {

    }

    public void deleteGame(String name) {

    }

    public void deleteCombinedStrategy(String name) {

    }

    public void deleteInitialization(String name) {

    }

    public Map<String, Collection<Configuration>> getConfigurations() {
        return configurations;
    }

    public Configuration getConfiguration(String name) {
        return null;
    }

    public Map<Integer, Agent> getAgents() {
        return agents;
    }

    public Agent getAgent(int id) {
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

}
