package de.sswis.controller;

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
    Object createMainView();

    /**
     * Erstellt ein Ergebnisansichtsfenster.
     * @return ein Ergebnisansichtsfenster
     */
    Object createShowResultView();

    /**
     * Erstellt ein Konfigurationsverwaltungsfenster.
     * @return ein Konfigurationsverwaltungsfenster
     */
    Object createManageConfigurationsView();

    /**
     * Erstellt ein Initialisierungsverwaltungsfenster.
     * @return ein Initialisierungsverwaltungsfenster
     */
    Object createManageInitializationsView();

    /**
     * Erstellt ein Strategieverwaltungsfenster.
     * @return ein Strategieverwaltungsfenster
     */
    Object createManageStrategiesView();

    /**
     * Erstellt ein Spieleverwaltungsfenster.
     * @return ein Spieleverwaltungsfenster
     */
    Object createManageGamesView();

    /**
     * Erstellt ein Ergebnisverwaltungsfenster.
     * @return ein Ergebnisverwaltungsfenster
     */
    Object createManageResultsView();

    /**
     * Erstellt ein Fenster zum Erstellen von Konfigurationen.
     * @return ein Fenster zum Erstellen von Konfigurationen
     */
    Object createNewConfigurationView();

    /**
     * Erstellt ein Fenster zum Erstellen von Initialisierungen.
     * @return ein Fenster zum Erstellen von Initialisierungen
     */
    Object createNewInitializationView();

    /**
     * Erstellt ein Fenster zum Erstellen von Strategien.
     * @return ein Fenster zum Erstellen von Strategien
     */
    Object createNewStrategyView();

    /**
     * Erstellt ein Fenster zum Erstellen von Spielen.
     * @return ein Fenster zum Erstellen von Spielen
     */
    Object createNewGameView();

}
