package de.sswis.view.model;

import java.util.List;

/**
 * Kombinierte Strategie Daten, die alle nötigen Parameter zum Erzeugen einer {@code CombinedStrategy} enthält.
 * Erhält Nutzereingaben von der Benutzeroberfäche und prüft diese auf Konsistenz und Korrektheit.
 *
 * @author Sophie Bräuniger
 */
public class VMCombinedStrategy {
    private String name;
    private String description;

    private List<String> conditions;
    private List<String> strategies;


    /**
     * Zeigt ob die gespeicherten Daten konsistent und korrekt sind.
     * Fehlerhafte Daten beinhalten: illegale Eingaben.
     *
     * @return true wenn die Daten korrekt sind und false wenn sie fehlerhaft sind.
     */
    public boolean isCorrect () {
        //TODO: implement me
        return false;
    }

    /**
     * Fügt ein Paar von Bedingung und Basisstrategie hinzu.
     * @param strategyName Name der Strategie
     * @param condition Bedingung
     */
    public void addStrategy(String strategyName, String condition) {}

    /**
     * Fügt ein Paar bestehend aus einer Bedingung, die eine weitere Eingabe x benötigt,
     * und einer Basisstrategie  hinzu.
     * @param strategyName Name der Strategie
     * @param condition Bedingung
     * @param x Parameter der Bedingung
     */
    public void addStrategy(String strategyName, String condition, double x) {}

    /**
     * Fügt ein Paar bestehend aus einer Bedingung, die eine weitere Eingabe x benötigt,
     * und einer Basisstrategie  hinzu.
     * @param strategyName Name der Strategie
     * @param condition Bedingung
     * @param x Parameter der Bedingung
     */
    public void addStrategy(String strategyName, String condition, int x) {}

    /**
     * Setzt die Standard Strategie, die die niedrigster Priorität und keine Bedingung hat.
     * @param strategyName Name der Strategie
     */
    public void setDefaultStrategy(String strategyName) {}


    /**
     * Gibt eine String der wichtige Informationen zu dieser kombinierten Strategie zusammenfasst.
     * @return String enthält Kurzbeschreibung der kombinierten Strategie
     */
    public String getToolTipText() {    return ""; }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
