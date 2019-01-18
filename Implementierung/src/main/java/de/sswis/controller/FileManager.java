package de.sswis.controller;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.stream.JsonReader;
import de.sswis.view.model.*;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collection;

/**
 * Verwaltet {@code ViewModel} Objekte in Dateien. Dies beinhaltet das Speichern, Löschen, Laden eines Objekts
 * und Laden aller Objekte eines Typs. Die {@code ViewModel} Objekte werden alle in einer eigenen Datei als
 * Json-String gespeichert. Der Dateiname ist definiert als {@code <Objekttyp>_<Objektname>.json}.
 * Das Speicherverzeichnis befindet sich in der jar-Datei und kann nicht geändert werden.
 *
 * @author Max Braun
 */
public class FileManager {

    private Gson gson;

    public static final String BASE_PATH            = "src/main/resources/saves";

    public static final String VM_CONFIGURATION     = "VMConfiguration";
    public static final String VM_INITIALIZATION    = "VMInitialization";
    public static final String VM_GAME              = "VMGame";
    public static final String VM_COMBINED_STRATEGY = "VMCombinedStrategy";
    public static final String VM_RESULT            = "VMResult";
    public static final String VM_STRATEGY          = "VMStrategy";
    public static final String JSON_EXTENSION       = ".json";

    /**
     * Standardkonstruktor
     */
    public FileManager() {
        this.gson = new GsonBuilder().create();
    }

    /**
     * Lädt alle vorhandenen {@code VMConfiguration} Objekte.
     *
     * @return eine {@code Collection} von {@code VMConfigurations}
     */
    public Collection<VMConfiguration> loadAllConfigurations() {
        File dir = new File(FileManager.BASE_PATH);
        File[] filesInDir = dir.listFiles();
        ArrayList<VMConfiguration> configurations = new ArrayList<>();

        for (File file : filesInDir) {
            if (file.isDirectory())
                continue;
            if (file.getName().startsWith(FileManager.VM_CONFIGURATION)) {
                JsonReader reader = null;
                try {
                    reader = new JsonReader(new FileReader(file));
                } catch (FileNotFoundException e) {
                    // this exception should never occur, if it doesn't exist it shouldn't have been loaded
                    // in dir.listFiles()
                    e.printStackTrace();
                }
                configurations.add(this.loadConfiguration(reader));
            }
        }
        return configurations;
    }

    /**
     * Lädt alle vorhandenen {@code VMGame} Objekte.
     *
     * @return eine {@code Collection} von {@code VMGame}
     */
    public Collection<VMGame> loadAllGames() {
        File dir = new File(FileManager.BASE_PATH);
        File[] filesInDir = dir.listFiles();
        ArrayList<VMGame> games = new ArrayList<>();

        for (File file : filesInDir) {
            if (file.isDirectory())
                continue;
            if (file.getName().startsWith(FileManager.VM_GAME)) {
                JsonReader reader = null;
                try {
                    reader = new JsonReader(new FileReader(file));
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
                games.add(this.loadGame(reader));
            }
        }
        return games;
    }

    /**
     * Lädt alle vorhandenen {@code VMInitialization} Objekte.
     *
     * @return eine {@code Collection} von {@code VMInitialization}
     */
    public Collection<VMInitialization> loadAllInitializations() {
        File dir = new File(FileManager.BASE_PATH);
        File[] filesInDir = dir.listFiles();
        ArrayList<VMInitialization> initializations = new ArrayList<>();

        for (File file : filesInDir) {
            if (file.isDirectory())
                continue;
            if (file.getName().startsWith(FileManager.VM_INITIALIZATION)) {
                JsonReader reader = null;
                try {
                    reader = new JsonReader(new FileReader(file));
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
                initializations.add(this.loadInitialization(reader));
            }
        }
        return initializations;
    }

    /**
     * Lädt alle vorhandenen {@code VMCombinedStrategy} Objekte.
     *
     * @return eine {@code Collection} von {@code VMCombinedStrategy}
     */
    public Collection<VMCombinedStrategy> loadAllCombinedStrategies() {
        File dir = new File(FileManager.BASE_PATH);
        File[] filesInDir = dir.listFiles();
        ArrayList<VMCombinedStrategy> combinedStrategies = new ArrayList<>();

        for (File file : filesInDir) {
            if (file.isDirectory())
                continue;
            if (file.getName().startsWith(FileManager.VM_COMBINED_STRATEGY)) {
                JsonReader reader = null;
                try {
                    reader = new JsonReader(new FileReader(file));
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
                combinedStrategies.add(this.loadCombinedStrategy(reader));
            }
        }
        return combinedStrategies;
    }

    /**
     * Lädt alle vorhandenen {@code VMStrategy} Objekte.
     *
     * @return eine {@code Collection} von {@code VMStartegy}
     */
    public Collection<VMStrategy> loadAllMixedStrageyies() {
        File dir = new File(FileManager.BASE_PATH);
        File[] filesInDir = dir.listFiles();
        ArrayList<VMStrategy> mixedStrategies = new ArrayList<>();

        for (File file : filesInDir) {
            if (file.isDirectory())
                continue;
            if (file.getName().startsWith(FileManager.VM_STRATEGY)) {
                JsonReader reader = null;
                try {
                    reader = new JsonReader(new FileReader(file));
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
                mixedStrategies.add(this.loadMixedStrategy(reader));
            }
        }
        return mixedStrategies;
    }

    /**
     * Lädt alle vorhandenen {@code VMResult} Objekte.
     *
     * @return eine {@code Collection} von {@code VMResult}
     */
    public Collection<VMResult> loadAllResults() {
        File dir = new File(FileManager.BASE_PATH);
        File[] filesInDir = dir.listFiles();
        ArrayList<VMResult> results = new ArrayList<>();

        for (File file : filesInDir) {
            if (file.isDirectory())
                continue;
            if (file.getName().startsWith(FileManager.VM_RESULT)) {
                JsonReader reader = null;
                try {
                    reader = new JsonReader(new FileReader(file));
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
                results.add(this.loadResult(reader));
            }
        }
        return results;
    }

    /**
     * Speichert die gegebende {@code VMConfiguration} in einer Datei.
     *
     * @param configuration die zu speichernde {@code VMConfiguration}
     */
    public void saveConfiguration(VMConfiguration configuration) throws IOException {
        String filePath = this.getFilePath(FileManager.VM_CONFIGURATION, configuration.getName());
        try (Writer writer = new FileWriter(filePath)) {
            this.gson.toJson(configuration, writer);
        }
    }

    /**
     * Speichert das gegebende {@code VMGame} in einer Datei.
     *
     * @param game das zu speichernde {@code VMGame}
     */
    public void saveGame(VMGame game) throws IOException {
        String filePath = this.getFilePath(FileManager.VM_GAME, game.getName());
        try (Writer writer = new FileWriter(filePath)) {
            this.gson.toJson(game, writer);
        }
    }

    /**
     * Speichert die gegebende {@code VMInitialization} in einer Datei.
     *
     * @param initialization die zu speichernde {@code VMInitialization}
     */
    public void saveInitalization(VMInitialization initialization) throws IOException {
        String filePath = this.getFilePath(FileManager.VM_INITIALIZATION, initialization.getName());
        try (Writer writer = new FileWriter(filePath)) {
            this.gson.toJson(initialization, writer);
        }
    }

    /**
     * Speichert die gegebende {@code VMCombinedStrategy} in einer Datei.
     *
     * @param combinedStrategy die zu speichernde {@code VMCombinedStrategy}
     */
    public void saveCombinedStrategy(VMCombinedStrategy combinedStrategy) throws IOException {
        String filePath = this.getFilePath(FileManager.VM_COMBINED_STRATEGY, combinedStrategy.getName());
        try (Writer writer = new FileWriter(filePath)) {
            this.gson.toJson(combinedStrategy, writer);
        }
    }

    /**
     * Speichert die gegebende {@code VMStrategy} in einer Datei.
     *
     * @param strategy die zu speichernde {@code VMStrategy}
     */
    public void saveMixedStrategy(VMStrategy strategy) throws IOException {
        String filePath = this.getFilePath(FileManager.VM_STRATEGY, strategy.getName());
        try (Writer writer = new FileWriter(filePath)) {
            this.gson.toJson(strategy, writer);
        }
    }

    /**
     * Speichert das gegebende {@code VMResult} in einer Datei.
     *
     * @param result das zu speichernde {@code VMResult}
     */
    public void saveResult(VMResult result) throws IOException {
        String filePath = this.getFilePath(FileManager.VM_RESULT, result.getName());
        try (Writer writer = new FileWriter(filePath)) {
            this.gson.toJson(result, writer);
        }
    }

    /**
     * Lädt die {@code VMConfiguration} mit dem angegebenen Namen. Der Name wird vom Benutzer beim Erstellen der
     * Konfiguration festegelegt.
     *
     * @param name der Name der {@code VMConfiguration}
     * @return die {@code VMConfiguration} mit dem angegebenen Namen
     */
    public VMConfiguration loadConfiguration(String name) throws FileNotFoundException {
        String filePath = this.getFilePath(FileManager.VM_CONFIGURATION, name);
        JsonReader jsonReader = new JsonReader(new FileReader(filePath));
        return this.loadConfiguration(jsonReader);
    }

    private VMConfiguration loadConfiguration(JsonReader reader) {
        return this.gson.fromJson(reader, VMConfiguration.class);
    }

    /**
     * Lädt das {@code VMGame} mit dem angegebenen Namen. Der Name wird vom Benutzer beim Erstellen des
     * Stufenspiels festegelegt.
     *
     * @param name der Name des {@code VMGame}
     * @return das {@code VMGame} mit dem angegebenen Namen
     */
    public VMGame loadGame(String name) throws FileNotFoundException {
        String filePath = this.getFilePath(FileManager.VM_GAME, name);
        JsonReader jsonReader = new JsonReader(new FileReader(filePath));
        return this.loadGame(jsonReader);
    }

    private VMGame loadGame(JsonReader reader) {
        return this.gson.fromJson(reader, VMGame.class);
    }

    /**
     * Lädt die {@code VMInitialization} mit dem angegebenen Namen. Der Name wird vom Benutzer beim Erstellen der
     * Initialisierung festegelegt.
     *
     * @param name der Name der {@code VMInitialization}
     * @return die {@code VMInitialization} mit dem angegebenen Namen
     */
    public VMInitialization loadInitialization(String name) throws FileNotFoundException {
        String filePath = this.getFilePath(FileManager.VM_INITIALIZATION, name);
        JsonReader jsonReader = new JsonReader(new FileReader(filePath));
        return this.loadInitialization(jsonReader);
    }

    private VMInitialization loadInitialization(JsonReader reader) {
        return this.gson.fromJson(reader, VMInitialization.class);
    }

    /**
     * Lädt die {@code VMCombinedStrategy} mit dem angegebenen Namen. Der Name wird vom Benutzer beim Erstellen der
     * kombinierten Strategie festegelegt.
     *
     * @param name der Name der {@code VMCombinedStrategy}
     * @return die {@code VMCombinedStrategy} mit dem angegebenen Namen
     */
    public VMCombinedStrategy loadCombinedStrategy(String name) throws FileNotFoundException {
        String filePath = this.getFilePath(FileManager.VM_COMBINED_STRATEGY, name);
        JsonReader jsonReader = new JsonReader(new FileReader(filePath));
        return this.loadCombinedStrategy(jsonReader);
    }

    private VMCombinedStrategy loadCombinedStrategy(JsonReader reader) {
        return this.gson.fromJson(reader, VMCombinedStrategy.class);
    }

    /**
     * Lädt die {@code VMStrategy} mit dem angegebenen Namen. Der Name wird vom Benutzer beim Erstellen der
     * gemischten Strategie festegelegt.
     *
     * @param name der Name der {@code VMStrategy}
     * @return die {@code VMStrategy} mit dem angegebenen Namen
     */
    public VMStrategy loadMixedStrategy(String name) throws FileNotFoundException {
        String filePath = this.getFilePath(FileManager.VM_STRATEGY, name);
        JsonReader jsonReader = new JsonReader(new FileReader(filePath));
        return this.loadMixedStrategy(jsonReader);
    }

    private VMStrategy loadMixedStrategy(JsonReader reader) {
        return this.gson.fromJson(reader, VMStrategy.class);
    }

    /**
     * Lädt das {@code VMResult} mit dem angegebenen Namen. Der Name entspricht der zugehörigen
     * {@code Configuration} aus deren Simulation das Ergebnis entstanden ist.
     *
     * @param name der Name des {@code VMResult}
     * @return das {@code VMResult} mit dem angegebenen Namen
     */
    public VMResult loadResult(String name) throws FileNotFoundException {
        String filePath = this.getFilePath(FileManager.VM_RESULT, name);
        JsonReader jsonReader = new JsonReader(new FileReader(filePath));
        return this.loadResult(jsonReader);
    }

    private VMResult loadResult(JsonReader reader) {
        return this.gson.fromJson(reader, VMResult.class);
    }

    /**
     * Löscht die gespeicherte {@code VMConfiguration} mit dem angegebenen Namen. Der Name wird vom Benutzer beim
     * Erstellen der Konfiguration festegelegt.
     *
     * @param name der Name der {@code VMConfiguration}
     */
    public void deleteConfiguration(String name) throws IOException {
        Path path = Paths.get(this.getFilePath(FileManager.VM_CONFIGURATION, name));
        Files.deleteIfExists(path);
    }

    /**
     * Löscht das gespeicherte {@code VMGame} mit dem angegebenen Namen. Der Name wird vom Benutzer beim
     * Erstellen des Stufenspiels festegelegt.
     *
     * @param name der Name des {@code VMGame}
     */
    public void deleteGame(String name) throws IOException {
        Path path = Paths.get(this.getFilePath(FileManager.VM_GAME, name));
        Files.deleteIfExists(path);
    }

    /**
     * Löscht die gespeicherte {@code VMInitialisation} mit dem angegebenen Namen. Der Name wird vom Benutzer beim
     * Erstellen der Initialisierung festegelegt.
     *
     * @param name der Name der {@code VMInitialisation}
     */
    public void deleteInitalization(String name) throws IOException {
        Path path = Paths.get(this.getFilePath(FileManager.VM_INITIALIZATION, name));
        Files.deleteIfExists(path);
    }

    /**
     * Löscht die gespeicherte {@code VMCombinedStrategy} mit dem angegebenen Namen. Der Name wird vom Benutzer beim
     * Erstellen der kombinierten Strategie festegelegt.
     *
     * @param name der Name der {@code VMCombinedStrategy}
     */
    public void deleteCombinedStrategy(String name) throws IOException {
        Path path = Paths.get(this.getFilePath(FileManager.VM_COMBINED_STRATEGY, name));
        Files.deleteIfExists(path);
    }

    /**
     * Löscht die gespeicherte {@code VMStrategy} mit dem angegebenen Namen. Der Name wird vom Benutzer beim
     * Erstellen der kombinierten Strategie festegelegt.
     *
     * @param name der Name der {@code VMStrategy}
     */
    public void deleteMixedStrategy(String name) throws IOException {
        Path path = Paths.get(this.getFilePath(FileManager.VM_STRATEGY, name));
        Files.deleteIfExists(path);
    }

    /**
     * Löscht das gespeicherte {@code VMResult} mit dem angegebenen Namen. Der Name entspricht der zugehörigen
     * {@code Configuration} aus deren Simulation das Ergebnis entstanden ist.
     *
     * @param name der Name des {@code VMResult}
     */
    public void deleteResult(String name) throws IOException {
        Path path = Paths.get(this.getFilePath(FileManager.VM_RESULT, name));
        Files.deleteIfExists(path);
    }

    private String getFilePath(String objectType, String objectName) {
        return FileManager.BASE_PATH + "/" + objectType + "_" + objectName + FileManager.JSON_EXTENSION;
    }
}
