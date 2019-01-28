package de.sswis.view.model;

import java.util.List;

/**
 * Gemischte Strategie Daten, die alle nötigen Parameter zum Erzeugen einer {@code Strategy} enthält.
 * Erhält Nutzereingaben von der Benutzeroberfäche und prüft diese auf Konsistenz und Korrektheit.
 *
 * @author Sophie Bräuniger
 */
public class VMStrategy {

    private String name;
    private String description;


    private List<String> combinedStrategies;
    private List<String> probabilities;

    /**
     * Zeigt ob die gespeicherten Daten konsistent und korrekt sind.
     * Fehlerhafte Daten beinhalten: illegale Eingaben, Inkonsistenzen innerhalb der Wahrscheinlichkeiten.
     *
     * @return true wenn die Daten korrekt sind und false wenn sie fehlerhaft sind.
     */
    public boolean isCorrect () {
        //TODO: implement me
        return false;
    }

    /**
     * Fügt ein Paar von kombinierte Strategie und Wahrscheinlichkeit hinzu.
     *
     * @param strategyName Name der Strategie
     * @param probability Wahrscheinlichkeit
     */
    public void addStrategy (String strategyName, double probability) {}

    public List<String> getCombinedStrategies() {
        return combinedStrategies;
    }

    public List<String> getProbabilities() {
        return probabilities;
    }

    /**
     * Gibt eine String der wichtige Informationen zu dieser gemischten Strategie zusammenfasst.
     * @return String enthält Kurzbeschreibung der gemischten Strategie
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
