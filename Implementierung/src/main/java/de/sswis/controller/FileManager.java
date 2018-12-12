package de.sswis.controller;

import com.google.gson.Gson;

/**
 * @author Max Braun
 */
public class FileManager {

    private Gson gson;

    public FileManager() {
        gson = new Gson();
    }

    public void saveConfiguration(Object obj) {

    }

    public void saveGame(Object obj) {

    }

    public void saveInitalization(Object obj) {

    }

    public void saveCombinedStrategy(Object obj) {

    }

    public void saveResult(Object obj) {

    }

    public Object loadConfiguration(String name) {
        return null;
    }

    public Object loadGame(String name) {
        return null;
    }

    public Object loadInitalization(String name) {
        return null;
    }

    public Object loadCombinedStrategy(String name) {
        return null;
    }

    public Object loadResult(String name) {
        return null;
    }

    public void deleteConfiguration(Object obj) {

    }

    public void deleteGame(Object obj) {

    }

    public void deleteInitalization(Object obj) {

    }

    public void deleteCombinedStrategy(Object obj) {

    }

    public void deleteResult(Object obj) {

    }

}
