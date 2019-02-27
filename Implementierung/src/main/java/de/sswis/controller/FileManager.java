package de.sswis.controller;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import de.sswis.view.model.*;

import java.io.*;
import java.lang.reflect.Type;
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

    // Path is relative to resources directory
    private static final String SAVES_PATH = "./saves";

    private static final String VM_CONFIGURATION     = "VMConfiguration";
    private static final String VM_INITIALIZATION    = "VMInitialization";
    private static final String VM_GAME              = "VMGame";
    private static final String VM_COMBINED_STRATEGY = "VMCombinedStrategy";
    private static final String VM_RESULT            = "VMResult";
    private static final String VM_STRATEGY          = "VMStrategy";
    private static final String JSON_EXTENSION       = ".json";

    /**
     * Standardkonstruktor
     */
    public FileManager() {
        File saveDir = new File(FileManager.SAVES_PATH);
        if (!saveDir.isDirectory()) {
            saveDir.mkdir();
        }
        this.gson = new GsonBuilder().create();
    }

    private File[] getFilesInSaves() {
        File dir = new File(FileManager.SAVES_PATH);
        return dir.listFiles();
    }

    /**
     * Lädt alle vorhandenen {@code VMConfiguration} Objekte.
     *
     * @return eine {@code Collection} von {@code VMConfigurations}
     */
    public Collection<VMConfiguration> loadAllConfigurations() {
        File[] filesInDir = this.getFilesInSaves();
        assert filesInDir != null;

        ArrayList<VMConfiguration> configurations = new ArrayList<>();
        for (File file : filesInDir) {
            if (file.isDirectory())
                continue;
            if (file.getName().startsWith(FileManager.VM_CONFIGURATION)) {
                try (JsonReader reader = new JsonReader(new FileReader(file))) {
                    configurations.add(this.loadConfiguration(reader));
                } catch (IOException e) {
                    e.printStackTrace();
                }
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
        File[] filesInDir = this.getFilesInSaves();
        assert filesInDir != null;

        ArrayList<VMGame> games = new ArrayList<>();
        for (File file : filesInDir) {
            if (file.isDirectory())
                continue;
            if (file.getName().startsWith(FileManager.VM_GAME)) {
                try (JsonReader reader = new JsonReader(new FileReader(file))) {
                    games.add(this.loadGame(reader));
                } catch (IOException e) {
                    e.printStackTrace();
                }
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
        File[] filesInDir = this.getFilesInSaves();
        assert filesInDir != null;

        ArrayList<VMInitialization> initializations = new ArrayList<>();
        for (File file : filesInDir) {
            if (file.isDirectory())
                continue;
            if (file.getName().startsWith(FileManager.VM_INITIALIZATION)) {
                try (JsonReader reader = new JsonReader(new FileReader(file))) {
                    initializations.add(this.loadInitialization(reader));
                } catch (IOException e) {
                    e.printStackTrace();
                }
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
        File[] filesInDir = this.getFilesInSaves();
        assert filesInDir != null;

        ArrayList<VMCombinedStrategy> combinedStrategies = new ArrayList<>();
        for (File file : filesInDir) {
            if (file.isDirectory())
                continue;
            if (file.getName().startsWith(FileManager.VM_COMBINED_STRATEGY)) {
                try (JsonReader reader = new JsonReader(new FileReader(file))) {
                    combinedStrategies.add(this.loadCombinedStrategy(reader));
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return combinedStrategies;
    }

    /**
     * Lädt alle vorhandenen {@code VMStrategy} Objekte.
     *
     * @return eine {@code Collection} von {@code VMStartegy}
     */
    public Collection<VMStrategy> loadAllMixedStrategies() {
        File[] filesInDir = this.getFilesInSaves();
        assert filesInDir != null;

        ArrayList<VMStrategy> mixedStrategies = new ArrayList<>();
        for (File file : filesInDir) {
            if (file.isDirectory())
                continue;
            if (file.getName().startsWith(FileManager.VM_STRATEGY)) {
                try (JsonReader reader = new JsonReader(new FileReader(file))) {
                    mixedStrategies.add(this.loadMixedStrategy(reader));
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return mixedStrategies;
    }

    /**
     * Lädt alle vorhandenen {@code VMResult} Objekte.
     *
     * @return eine {@code Collection} von {@code VMResult}
     */
    public Collection<Collection<VMResult>> loadAllResults() {
        File[] filesInDir = this.getFilesInSaves();
        assert filesInDir != null;
        Collection<Collection<VMResult>> results = new ArrayList<>();
        for (File file : filesInDir) {
            if (file.isDirectory())
                continue;
            if (file.getName().startsWith(FileManager.VM_RESULT)) {
                try (JsonReader reader = new JsonReader(new FileReader(file))) {
                    results.add(this.loadResults(reader));
                } catch (IOException e) {
                    e.printStackTrace();
                }
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
        deleteConfiguration(configuration.getName());
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
        deleteGame(game.getName());
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
    public void saveInitialization(VMInitialization initialization) throws IOException {
        deleteInitialization(initialization.getName());
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
        deleteCombinedStrategy(combinedStrategy.getName());
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
        deleteMixedStrategy(strategy.getName());
        String filePath = this.getFilePath(FileManager.VM_STRATEGY, strategy.getName());
        try (Writer writer = new FileWriter(filePath)) {
            this.gson.toJson(strategy, writer);
        }
    }

    /**
     * Speichert das gegebende {@code VMResult} in einer Datei.
     *
     * @param results das zu speichernde {@code VMResult}
     */
    public void saveResults(Collection<VMResult> results) throws IOException {
        if (results.isEmpty())
            return;
        VMResult first = results.iterator().next();
        deleteResult(first.getName());
        String filePath = this.getFilePath(FileManager.VM_RESULT, first.getName());
        try (Writer writer = new FileWriter(filePath)) {
            Type type = new TypeToken<Collection<VMResult>>() {}.getType();
            this.gson.toJson(results, type, writer);
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
        try (JsonReader jsonReader = new JsonReader(new FileReader(filePath))) {
            return this.loadConfiguration(jsonReader);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
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
        try (JsonReader jsonReader = new JsonReader(new FileReader(filePath))) {
            return this.loadGame(jsonReader);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
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
        try (JsonReader jsonReader = new JsonReader(new FileReader(filePath))) {
            return this.loadInitialization(jsonReader);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
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
        try (JsonReader jsonReader = new JsonReader(new FileReader(filePath))) {
            return this.loadCombinedStrategy(jsonReader);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
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
        try (JsonReader jsonReader = new JsonReader(new FileReader(filePath))) {
            return this.loadMixedStrategy(jsonReader);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
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
    public Collection<VMResult> loadResults(String name) throws FileNotFoundException {
        String filePath = this.getFilePath(FileManager.VM_RESULT, name);
        try (JsonReader jsonReader = new JsonReader(new FileReader(filePath))) {
            return this.loadResults(jsonReader);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    private Collection<VMResult> loadResults(JsonReader reader) {
        Type type = new TypeToken<Collection<VMResult>>() {}.getType();
        return this.gson.fromJson(reader, type);
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
    public void deleteInitialization(String name) throws IOException {
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
        return FileManager.SAVES_PATH + "/" + objectType + "_" + objectName + FileManager.JSON_EXTENSION;
    }
}
