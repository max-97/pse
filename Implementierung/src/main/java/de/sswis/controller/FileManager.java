package de.sswis.controller;

import com.google.gson.Gson;
import de.sswis.model.CombinedStrategy;
import de.sswis.model.Configuration;
import de.sswis.model.Game;
import de.sswis.model.Initialization;

import java.util.Collection;

/**
 * @author Max Braun
 */
public class FileManager {

    private Gson gson;

    public FileManager() {
        gson = new Gson();
    }

    public void saveConfigurations(Collection<Configuration> configurations) {

    }

    public void saveGame(Game game) {

    }

    public void saveInitalization(Initialization initialization) {

    }

    public void saveCombinedStrategy(CombinedStrategy combinedStrategy) {

    }

    public void saveResult(Object obj) {

    }

    public Collection<Configuration> loadConfigurations(String name) {
        return null;
    }

    public Game loadGame(String name) {
        return null;
    }

    public Initialization loadInitalization(String name) {
        return null;
    }

    public CombinedStrategy loadCombinedStrategy(String name) {
        return null;
    }

    public Object loadResult(String name) {
        return null;
    }

    public void deleteConfigurations(String name) {

    }

    public void deleteGame(String name) {

    }

    public void deleteInitalization(String name) {

    }

    public void deleteCombinedStrategy(String name) {

    }

    public void deleteResult(Object obj) {

    }

}
