package de.sswis.view.model;

import java.util.ArrayList;
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

    public VMStrategy() {
        name = "";
        description = "";
        combinedStrategies = new ArrayList<>();
        probabilities = new ArrayList<>();
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

    public String getDescription() {
        return description;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setCombinedStrategies(List<String> combinedStrategies) {
        this.combinedStrategies = combinedStrategies;
    }

    public void setProbabilities(List<String> probabilities) {
        this.probabilities = probabilities;
    }
}
