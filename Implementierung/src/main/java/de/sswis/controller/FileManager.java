package de.sswis.controller;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import de.sswis.view.model.*;

import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
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
    //TODO is this relative file path correct?
    public static final String BASE_PATH            = "src/main/resources/saves/";

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
        return null;
    }

    /**
     * Lädt alle vorhandenen {@code VMGame} Objekte.
     *
     * @return eine {@code Collection} von {@code VMGame}
     */
    public Collection<VMGame> loadAllGames() {
        return null;
    }

    /**
     * Lädt alle vorhandenen {@code VMInitialization} Objekte.
     *
     * @return eine {@code Collection} von {@code VMInitialization}
     */
    public Collection<VMInitialization> loadAllInitializations() {
        return null;
    }

    /**
     * Lädt alle vorhandenen {@code VMCombinedStrategy} Objekte.
     *
     * @return eine {@code Collection} von {@code VMCombinedStrategy}
     */
    public Collection<VMCombinedStrategy> loadAllCombinedStrategies() {
        return null;
    }

    /**
     * Lädt alle vorhandenen {@code VMResult} Objekte.
     *
     * @return eine {@code Collection} von {@code VMResult}
     */
    public Collection<VMResult> loadAllResults() {
        return null;
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
    public VMConfiguration loadConfiguration(String name) {
        return null;
    }

    /**
     * Lädt das {@code VMGame} mit dem angegebenen Namen. Der Name wird vom Benutzer beim Erstellen des
     * Stufenspiels festegelegt.
     *
     * @param name der Name des {@code VMGame}
     * @return das {@code VMGame} mit dem angegebenen Namen
     */
    public VMGame loadGame(String name) {
        return null;
    }

    /**
     * Lädt die {@code VMInitialization} mit dem angegebenen Namen. Der Name wird vom Benutzer beim Erstellen der
     * Initialisierung festegelegt.
     *
     * @param name der Name der {@code VMInitialization}
     * @return die {@code VMInitialization} mit dem angegebenen Namen
     */
    public VMInitialization loadInitalization(String name) {
        return null;
    }

    /**
     * Lädt die {@code VMCombinedStrategy} mit dem angegebenen Namen. Der Name wird vom Benutzer beim Erstellen der
     * kombinierten Strategie festegelegt.
     *
     * @param name der Name der {@code VMCombinedStrategy}
     * @return die {@code VMCombinedStrategy} mit dem angegebenen Namen
     */
    public VMCombinedStrategy loadCombinedStrategy(String name) {
        return null;
    }

    /**
     * Lädt das {@code VMResult} mit dem angegebenen Namen. Der Name entspricht der zugehörigen
     * {@code Configuration} aus deren Simulation das Ergebnis entstanden ist.
     *
     * @param name der Name des {@code VMResult}
     * @return das {@code VMResult} mit dem angegebenen Namen
     */
    public VMResult loadResult(String name) {
        return null;
    }

    /**
     * Löscht die gespeicherte {@code VMConfiguration} mit dem angegebenen Namen. Der Name wird vom Benutzer beim
     * Erstellen der Konfiguration festegelegt.
     *
     * @param name der Name der {@code VMConfiguration}
     */
    public void deleteConfiguration(String name) {

    }

    /**
     * Löscht das gespeicherte {@code VMGame} mit dem angegebenen Namen. Der Name wird vom Benutzer beim
     * Erstellen des Stufenspiels festegelegt.
     *
     * @param name der Name des {@code VMGame}
     */
    public void deleteGame(String name) {

    }

    /**
     * Löscht die gespeicherte {@code VMInitialisation} mit dem angegebenen Namen. Der Name wird vom Benutzer beim
     * Erstellen der Initialisierung festegelegt.
     *
     * @param name der Name der {@code VMInitialisation}
     */
    public void deleteInitalization(String name) {

    }

    /**
     * Löscht die gespeicherte {@code VMCombinedStrategy} mit dem angegebenen Namen. Der Name wird vom Benutzer beim
     * Erstellen der kombinierten Strategie festegelegt.
     *
     * @param name der Name der {@code VMCombinedStrategy}
     */
    public void deleteCombinedStrategy(String name) {

    }

    /**
     * Löscht das gespeicherte {@code VMResult} mit dem angegebenen Namen. Der Name entspricht der zugehörigen
     * {@code Configuration} aus deren Simulation das Ergebnis entstanden ist.
     *
     * @param name der Name des {@code VMResult}
     */
    public void deleteResult(String name) {

    }

    private String getFilePath(String objectType, String objectName) {
        return this.BASE_PATH + objectType + "_" + objectName + FileManager.JSON_EXTENSION;
    }
}
