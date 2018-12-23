package de.sswis.controller;

import de.sswis.view.*;

/**
 * Eine Fabrik zum Erzeugen von GUIs. Der Nutzer dieser Schnittstelle kann sich alle Benutzeroberflächen erzeugen
 * lassen, die in Sswis verwendet werden können. Die Methoden liefern alle eine Schnittstelle zurück, welches das
 * zu erzeugende Fenster beschreibt. In den Methoden werden die Instanzen der jeweiligen Klassen mit den
 * entsprechenden Schnittstellen erzeugt und die benötigten {@code ActionListener} gesetzt. Bestimmte
 * Implementierungen von Benutzeroberflächen können weitere oder andere Parameter benötigen.
 * <br>
 * Das {@code AbstractGuiFactory} Interface enthält eine Methode, um das Hauptfenster zu erzeugen.
 * <br>
 * Das {@code AbstractGuiFactory} Interface enthält eine Methode, um das Ergebnisansichtsfenster zu erzeugen.
 * <br>
 * Das {@code AbstractGuiFactory} Interface enthält fünf Methoden, um die Fenster zum Verwalten von Konfigurationen,
 * Initialisierungen, Strategien, Spielen und Ergebnissen zu erzeugen.
 * <br>
 * Das {@code AbstractGuiFactory} Interface enthält vier Methoden, um die Fenster zum Erstellen von neuen
 * Konfigurationen, Initialisierungen, Strategien und Spielen zu erzeugen.
 * @author Max Braun
 */
public interface AbstractGuiFactory {

    /**
     * Erstellt ein Hauptfenster.
     * @return ein Hauptfenster
     */
    AbstractMainView createMainView();

    /**
     * Erstellt ein Ergebnisansichtsfenster.
     * @return ein Ergebnisansichtsfenster
     */
    AbstractShowResultView createShowResultView();

    /**
     * Erstellt eine Ergebnisansicht zum Verlgeichen von Simulationen.
     * @return eine Ergebnisansicht zum Verlgeichen von Simulationen
     */
    AbstractShowCompareView createCompareResultsView();

    /**
     * Erstellt eine Ergebnisansicht mit allen Simulationen einer Multikonfiguration.
     * @return eine Ergebnisansicht mit allen Simulationen einer Multikonfiguration
     */
    AbstractShowMultiResultView createMultiResultsView();

    /**
     * Erstellt ein Konfigurationsverwaltungsfenster.
     * @return ein Konfigurationsverwaltungsfenster
     */
    AbstractManageConfigurationsView createManageConfigurationsView();

    /**
     * Erstellt ein Initialisierungsverwaltungsfenster.
     * @return ein Initialisierungsverwaltungsfenster
     */
    AbstractManageInitializationsView createManageInitializationsView();

    /**
     * Erstellt ein Strategieverwaltungsfenster.
     * @return ein Strategieverwaltungsfenster
     */
    AbstractManageStrategiesView createManageStrategiesView();

    /**
     * Erstellt ein Spieleverwaltungsfenster.
     * @return ein Spieleverwaltungsfenster
     */
    AbstractManageGamesView createManageGamesView();

    /**
     * Erstellt ein Ergebnisverwaltungsfenster.
     * @return ein Ergebnisverwaltungsfenster
     */
    AbstractManageResultsView createManageResultsView();

    /**
     * Erstellt ein Fenster zum Erstellen von Konfigurationen.
     * @return ein Fenster zum Erstellen von Konfigurationen
     */
    AbstractNewConfigurationView createNewConfigurationView();

    /**
     * Erstellt ein Fenster zum Erstellen von Initialisierungen.
     * @return ein Fenster zum Erstellen von Initialisierungen
     */
    AbstractNewInitializationView createNewInitializationView();

    /**
     * Erstellt ein Fenster zum Erstellen von Strategien.
     * @return ein Fenster zum Erstellen von Strategien
     */
    AbstractNewStrategyView createNewStrategyView();

    /**
     * Erstellt ein Fenster zum Erstellen von Spielen.
     * @return ein Fenster zum Erstellen von Spielen
     */
    AbstractNewGameView createNewGameView();

}
