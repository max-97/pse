package de.sswis.controller;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Max Braun
 */
public class ModelProvider {

    private static ModelProvider modelProvider;

    private HashMap<String, Object> configurations;
    private HashMap<Integer, Object> agents;
    private HashMap<String, Object> games;
    private HashMap<String, Object> combinedStrategies;

    private ModelProvider() {

    }

    public static ModelProvider getInstance() {
        if (modelProvider == null) {
            modelProvider = new ModelProvider();
        }
        return modelProvider;
    }

    public void addConfiguration(Object obj) {

    }

    public void addAgent(Object obj) {

    }

    public void addGame(Object obj) {

    }

    public void addCombinedStrategy(Object obj) {

    }

    public Map<String, Object> getConfigurations() {
        return configurations;
    }

    public Object getCOnfiguration(String name) {
        return null;
    }

    public Map<Integer, Object> getAgents() {
        return agents;
    }

    public Object getAgent(int id) {
        return null;
    }

    public Map<String, Object> getGames() {
        return games;
    }

    public Object getGame(String name) {
        return null;
    }

    public Map<String, Object> getCombinedStrategies() {
        return combinedStrategies;
    }

    public Object getCombinedStrategy(String name) {
        return null;
    }

}
